//package vn.bvntp.app.ui.activity
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Constraints
//import androidx.core.net.toUri
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelStore
//import com.example.ntp_app.ui.theme.AppNTPTheme
//
//import vn.bvntp.app.App
//import vn.bvntp.app.MainActivity
//import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel
//
//class PdfViewerActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val uri = intent.extras?.getString("fileUri")?.toUri() ?: Uri.EMPTY
//
//        Log.d("uri", uri.toString())
//        val pdfVerticallReaderState = VerticalPdfReaderState(
//            resource = ResourceType.Local(uri),
//            isZoomEnable = true,
//            isAccessibleEnable = true
//        )
//
//        setContent {
//            AppNTPTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                    VerticalPDFReader(
//                        state = pdfVerticallReaderState,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(color = Color.Gray)
//                    )
//                }
//            }
//        }
//    }
//}
