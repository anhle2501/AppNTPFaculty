import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState

import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.material3.Icon

import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.ImageBitmap

import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.text.isDigitsOnly
import com.shockwave.pdfium.PdfiumCore
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun renderPdfiumCore(context: Context, file: File, pageNumber: Int) : Bitmap {
//    val file = File(context.cacheDir, fileName)
    val pacelDescriptor = ParcelFileDescriptor.open(
        file,
        ParcelFileDescriptor.MODE_READ_ONLY
    )
    val pdfiumCore = PdfiumCore(context)
    val pageNum: Int = pageNumber
    var cbitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
    try {
        val pdf = pdfiumCore.newDocument(
            pacelDescriptor
        )
        pdfiumCore.openPage(pdf, pageNum)
        val screenWidth = context.resources.displayMetrics.widthPixels
        val width = pdfiumCore.getPageWidth(pdf, pageNum)
        val height = pdfiumCore.getPageHeight(pdf, pageNum)


        cbitmap = Bitmap.createBitmap(screenWidth,
            ((screenWidth.toFloat() / width) * height).toInt(),
            Bitmap.Config.RGB_565)
        pdfiumCore.renderPageBitmap(pdf, cbitmap, pageNumber, 0, 0,
            screenWidth,
            ((screenWidth.toFloat() / width) * height).toInt(),
            true)
        pdfiumCore.closeDocument(pdf)
        cbitmap.compress(Bitmap.CompressFormat.JPEG, 80, ByteArrayOutputStream())

    } catch (ex: IOException) {
        ex.printStackTrace()
        Toast.makeText(context, "Lỗi: " + ex.message, Toast.LENGTH_LONG)
            .show()
    }

    return cbitmap

}


fun addWatermark(originalBitmap: Bitmap, watermarkText: String): Bitmap {
    // Tạo một bitmap mới để chứa hình ảnh gốc và watermark
    val resultBitmap =
        Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, originalBitmap.config)

    // Tạo canvas để vẽ lên bitmap mới
    val canvas = Canvas(resultBitmap)

    // Vẽ hình ảnh gốc vào bitmap mới
    canvas.drawBitmap(originalBitmap, 0f, 0f, null)

    // Tạo một Paint để vẽ văn bản watermark
    val paint = Paint().apply {
        color = 0x40FF0000.toInt()// Màu sắc của watermark
        textSize = 40f // Kích thước của watermark
        isAntiAlias = true // Kích hoạt chế độ chống răng cưa để văn bản trông mượt mà hơn
    }

    // Tính toán kích thước của văn bản watermark
    val bounds = Rect()
    paint.getTextBounds(watermarkText, 0, watermarkText.length, bounds)

    // tinh toan water 1 dong

    val columnSpacing = 300 // Khoảng cách giữa các dòng chéo
    val rowSpacing = 300 // Khoảng cách giữa các dòng chéo
    val columnCount = (originalBitmap.width / columnSpacing) + 1
    val rowCount = (originalBitmap.height / rowSpacing) + 1

    // Vẽ văn bản watermark lên bitmap mới

    for (i in 0 until rowCount) {
        val y = i * rowSpacing.toFloat()

        for (j in 0 until columnCount) {
            val x = (j * columnSpacing).toFloat()

            canvas.rotate(-45f, x, y)
            canvas.drawText(watermarkText, x, y, paint)
            canvas.rotate(45f, x, y)
        }

    }
    return resultBitmap
}


fun renderPdfpage(context: Context, fileName: String, pageNumber: Int): Bitmap {
    val file = File(context.cacheDir, fileName)


//         old render by pdfrenderer api21
    val pacelDescriptor = ParcelFileDescriptor.open(
        file,
        ParcelFileDescriptor.MODE_READ_ONLY
    )
//        val pdfReader = PdfRenderer(
//            pacelDescriptor
//        )
//        val p = pdfReader.openPage(pageNumber)
//        val screenWidth = context.resources.displayMetrics.widthPixels
//        val bitmap = Bitmap.createBitmap(
//            screenWidth,
//            (screenWidth.toFloat() / p.width * p.height).toInt(),
//            Bitmap.Config.ARGB_8888
//        )
//        p.render(
//            bitmap, null, null,
//            Page.RENDER_MODE_FOR_DISPLAY
//        )

    // new render using pdfbox-android
    // Tạo một PDDocument mới
//    val document = PDDocument.load(file)
//
//    // Tạo một PDFRenderer mới từ PDDocument
//    val pdfRenderer = PDFRenderer(document)
//
//
//    val renderFile = File(context.cacheDir, "render1.jpg")
//    val fileOut = FileOutputStream(renderFile)
//    // Mở trang đầu tiên của PDF
//    val page = pdfRenderer.renderImage(pageNumber, 1f, ImageType.RGB)
//
//    val page2 = pdfRenderer.renderImage(pageNumber, 1f, ImageType.RGB).compress(Bitmap.CompressFormat.PNG, 0, fileOut)


//

//    page.compress(Bitmap.CompressFormat.JPEG, 100, fileOut)
//    fileOut.close()


    val renderBitMap = renderPdfiumCore(context, file, pageNumber)
    val watermarkBitmap = addWatermark(renderBitMap, "Bv. Nguyễn Tri Phương")

    return watermarkBitmap
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PDFReader(context: Context, uri: Uri) {
//    val assetsFile = context.assets.open("123456.pdf")
    val file = uri.toFile()
    val pacelDescriptor = ParcelFileDescriptor.open(
        file,
        ParcelFileDescriptor.MODE_READ_ONLY
    )

//    val outputStream = FileOutputStream(file)
//
//    outputStream.use { fileOut ->
//        assetsFile.copyTo(fileOut)
//    }
//    assetsFile.close()
//    outputStream.close()

    val pageCount = PdfRenderer(pacelDescriptor).pageCount

    var pagerState = rememberPagerState(pageCount = {
        pageCount
    })


    Column (verticalArrangement = Arrangement.Center){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
        ) { page ->
            // Our page content
            val watermarkBitmapTemp =
                renderPdfpage(LocalContext.current, "tempFileHsba.pdf", page)
                ZoomedPdf(watermarkBitmapTemp.asImageBitmap())
        }

        ButtonBar(pagerState = pagerState)
    }



}

@Preview
@Composable
fun PrePager() {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ButtonBar(pagerState: PagerState) {
    val activity = (LocalContext.current as Activity)
    val coroutine = rememberCoroutineScope()


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.Clear, contentDescription = "Previous 5 Page",
            modifier = Modifier
                .scale(1.5f)
                .clickable {
                    activity.finish()
                })
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous 5 Page",
            modifier = Modifier
                .scale(1.5f)
                .clickable {
                    coroutine.launch {
                        if (pagerState.currentPage - 5 > 0) {
                            pagerState.scrollToPage(pagerState.currentPage - 5)

                        } else {
                            pagerState.scrollToPage(0)

                        }

                    }

                }
            )
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Page",
            modifier = Modifier
                .scale(1.5f)
                .clickable {
                    coroutine.launch {
                        if (pagerState.currentPage - 1 > 0) {
                            pagerState.scrollToPage(pagerState.currentPage - 1)

                        } else {
                            pagerState.scrollToPage(0)

                        }

                    }
                }
            )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier){
            TextField(

                singleLine = true,
                value = (pagerState.currentPage + 1).toString() + " / ${pagerState.pageCount }",
                readOnly = true,
                onValueChange = {
                    coroutine.launch {
                        try {
                            if (it.isDigitsOnly()){
//
                                if (it.startsWith('0').and(it.length > 1)){
                                    pagerState.scrollToPage(it.subSequence(1,1).toString().toInt())

                                } else if (it.startsWith('0').and(it.length > 2)) {

                                    pagerState.scrollToPage(it.subSequence(1, 2).toString().toInt())

                                } else if (it.startsWith('0').and(it.length == 1)){
                                    pagerState.scrollToPage(0)
                                } else {

                                    pagerState.scrollToPage(it.toInt())
                                }
                            }
                            if (it.isNullOrEmpty()){
                                pagerState.scrollToPage(0)
                            }
                        } catch (e: NumberFormatException){
                            pagerState.scrollToPage(0)
                            Log.d("ButtonBar", "Bug" )
                        }


                    }
                },
                textStyle = TextStyle(textAlign = TextAlign.Center),
//                trailingIcon = { Text(text = "/ " + (pagerState.pageCount).toString(),
//                    modifier = Modifier.padding(end = 25.dp)
//                )},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.widthIn(min = 70.dp, max = 150.dp)
            )

        }

        Spacer(modifier = Modifier.padding(10.dp))
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Next Page",
            modifier = Modifier
                .scale(1.5f)
                .clickable {
                    coroutine.launch {
                        if (pagerState.currentPage + 1 > pagerState.pageCount) {
                            pagerState.scrollToPage(pagerState.pageCount)

                        } else {
                            pagerState.scrollToPage(pagerState.currentPage + 1)

                        }
                    }
                })
        Spacer(modifier = Modifier.padding(10.dp))
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next 5 Page",
            modifier = Modifier
                .scale(1.5f)
                .clickable {
                    coroutine.launch {
                        if (pagerState.currentPage + 5 > pagerState.pageCount) {
                            pagerState.scrollToPage(pagerState.pageCount)

                        } else {
                            pagerState.scrollToPage(pagerState.currentPage + 5)

                        }
                    }

                })
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
fun PrePdfViewer() {
    val pagerState = rememberPagerState(pageCount = {
1    })

    Column (verticalArrangement = Arrangement.Center){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .weight(0.6f)
        ) { page ->
            // Our page content
            val watermarkBitmapTemp = Bitmap.createBitmap( 1080, 2040, Bitmap.Config.RGB_565)


            ZoomedPdf(watermarkBitmapTemp.asImageBitmap())

        }
        ButtonBar(pagerState)

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, backgroundColor = 0xFF00FF00)
@Composable
fun PreButtonBar() {

    ButtonBar(pagerState = rememberPagerState(pageCount = {
        1
    }))
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true,
    backgroundColor = 0xFF00FF00, device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun PreButtonBarLandScape() {

    val pagerState = rememberPagerState(pageCount = {
        1    })

    Column (verticalArrangement = Arrangement.Center){
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .weight(0.6f)
        ) { page ->
            // Our page content
            val watermarkBitmapTemp = Bitmap.createBitmap( 2040, 1080, Bitmap.Config.RGB_565)


            ZoomedPdf(watermarkBitmapTemp.asImageBitmap())

        }
        ButtonBar(pagerState = rememberPagerState(pageCount = {
            1
        }))
    }
}

@Composable
fun ZoomedPdf(bitmap: ImageBitmap) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    Image(
        bitmap = bitmap,
        contentDescription = "Pdf page",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier

            .pointerInput(Unit) {
                detectTransformGestures(true) { _, pan, zoom, _ ->

                    // Update the scale based on zoom gestures.
                    scale *= zoom

                    // Limit the zoom levels within a certain range (optional).
                    scale = scale.coerceIn(0.5f, 3f)

                    // Update the offset to implement panning when zoomed.
                    // Update the offset to implement panning when zoomed.
                    offset = if (scale == 1f) Offset(0f, 0f) else offset + pan
                }
            }
            .graphicsLayer(
                scaleX = scale, scaleY = scale,
                translationX = offset.x, translationY = offset.y
            )

            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tapCenter ->
                        if (scale > 1.0f) {
                            scale = 1.0f
                            offset = Offset(0f, 0f)
                        } else {
                            scale = 3.0f
                            val center = Pair(720 / 2, 1280 / 2)
                            val xDiff = (tapCenter.x - center.first) * scale
                            val yDiff = ((tapCenter.y - center.second) * scale).coerceIn(
                                minimumValue = -(center.second * 2f),
                                maximumValue = (center.second * 2f)
                            )
                            offset = Offset(-xDiff, -yDiff)
                        }
                    }
                )

            }
    )

}


