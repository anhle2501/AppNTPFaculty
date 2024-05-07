package vn.bvntp.app.ui.activity

//import PDFReader



import PDFReader
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.ntp_app.ui.theme.AppNTPTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.dp
import com.rizzi.bouquet.rememberVerticalPdfReaderState

import vn.bvntp.app.App

import vn.bvntp.app.ui.view.HoSoBenhAnView
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.stream.IntStream


class HoSoBenhAnActivity: ComponentActivity() {


    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (applicationContext as App).container
        val viewModel =  ViewModelProvider(this, appContainer.hsbaViewModelFactory).get(
            HoSoBenhAnViewModel::class.java)
        var barcodeLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
            ScanContract()
        ) { result ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                viewModel.updateMaBenhNhan(result.contents.toString())
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                    .show()
            }
        }
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
        options.setPrompt("Quét mã bệnh nhân")
        options.setCameraId(0)  // Use a specific camera of the device
        options.setBeepEnabled(true)
        options.setBarcodeImageEnabled(true)
        val barcodeLauncherWithOption = {  barcodeLauncher.launch(options) }


        setContent {
            AppNTPTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                        HoSoBenhAnView(LocalContext.current, viewModel, barcodeLauncherWithOption)
                }
            }
        }
    }
}


