import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.graphics.pdf.PdfRenderer.Page
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import java.io.File
import java.io.FileOutputStream
import java.util.stream.IntStream.range


@Composable
fun PDFReader(context: Context) {



        val assetsFile = context.assets.open("123456.pdf")
        val file = File(context.cacheDir, "tempFile.pdf")
        val outputStream = FileOutputStream(file)

    outputStream.use { fileOut ->
            assetsFile.copyTo(fileOut)
        }

        val pacelDescriptor =  ParcelFileDescriptor.open(
            file,
            ParcelFileDescriptor.MODE_READ_ONLY
        )
        val pdfReader = PdfRenderer(
            pacelDescriptor
        )

        val arrayBitmap = ArrayList<Bitmap>()

    val screenWidth = context.resources.displayMetrics.widthPixels



        for (i in range(0, pdfReader.pageCount)){
            val p = pdfReader.openPage(i)

            val bitmap = Bitmap.createBitmap(
                screenWidth, (screenWidth.toFloat() / p.width * p.height).toInt(), Bitmap.Config.ARGB_8888
            )


//            val bitmap = Bitmap.createBitmap(
//                p.width,
//                p.height,
//                Bitmap.Config.ARGB_8888
//            )

            p.render(
                bitmap, null, null,
                Page.RENDER_MODE_FOR_DISPLAY
            )
            arrayBitmap.add(bitmap)
            p.close()
        }

    LazyColumn{

        items(arrayBitmap){ it ->
            ZoomedPdf(it.asImageBitmap())
        }
    }


//
//                        count++
//                    }
//                }
//            }

        file.delete()
        pacelDescriptor.close()
        assetsFile.close()
        outputStream.close()


}


@Composable
fun ZoomedPdf(bitmap: ImageBitmap){
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    Image(
        bitmap = bitmap,
        contentDescription = "Pdf page " ,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    // Update the scale based on zoom gestures.
                    scale *= zoom

                    // Limit the zoom levels within a certain range (optional).
                    scale = scale.coerceIn(0.5f, 3f)

                    // Update the offset to implement panning when zoomed.
                    if(offset.x > 0){
                        offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                    }

                    if(offset.x < 0) {
                        offset = if (scale == 1f) Offset(0f, 0f) else offset - pan
                    }
                }
            }
            .graphicsLayer(
                scaleX = scale, scaleY = scale,
                translationX = offset.x, translationY = offset.y
            )
    )

//    Button(
//        onClick = {
//            scale = 1f
//            offset = Offset(0f, 0f)
//        }
//    ) {
//        Text(text = "Reset Zoom")
//    }
}