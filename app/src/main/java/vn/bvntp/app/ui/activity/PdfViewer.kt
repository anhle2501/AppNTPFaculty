package vn.bvntp.app.ui.activity


import PDFReader
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.example.ntp_app.ui.theme.AppNTPTheme


class PdfViewer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.extras?.getString("fileUri")?.toUri() ?: Uri.EMPTY
        Log.d("PdfViewer", uri.toString())
        setContent {
            AppNTPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PDFReader(context = LocalContext.current, uri = uri)

                }

            }
        }
    }
}

@Preview
@Composable
fun PrePDFReader(){
//    PDFReader(context = LocalContext.current)
}