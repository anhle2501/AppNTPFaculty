package vn.bvntp.app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ntp_app.ui.theme.AppNTPTheme
import com.github.barteksc.pdfviewer.PDFView
//import com.tom_roush.pdfbox.pdmodel.PDDocument
//import com.tom_roush.pdfbox.rendering.PDFRenderer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.hasAccessToken
import vn.bvntp.app.ui.activity.HoSoBenhAnActivity
import vn.bvntp.app.ui.activity.LoginActivity
import vn.bvntp.app.ui.activity.PdfViewer
import vn.bvntp.app.ui.component.Logo
import java.io.File
import java.io.FileInputStream

class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.layout)
//        val pdfView = findViewById<PDFView>(R.id.pdfView);
//
//        pdfView.fromAsset("123456.pdf").enableAnnotationRendering(true).load()



        // Tạo Intent để chuyển đến Activity khác
        setContent{
            AppNTPTheme {
                // A surface container using the 'background' color from the theme
                Surface{
                    Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ){
                        Logo(modifier = Modifier
//                        .fillMaxSize()
                            .size(300.dp, 300.dp)
                        )
                    }

                }
            }
        }
//        // Mở file PDF
//        val file = File(cacheDir, "tempFile.pdf")
//
//// Tạo một PDDocument mới
//        val document = PDDocument.load(file)
//
//// Tạo một PDFRenderer mới từ PDDocument
//        val pdfRenderer = PDFRenderer(document)
//
//// Mở trang đầu tiên của PDF
//        val page = pdfRenderer.renderImage(0)
//
//// Tạo một Bitmap để chứa nội dung của trang
//        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
//
//// Vẽ nội dung của trang PDF lên Bitmap
//        val canvas = Canvas(bitmap)
//        canvas.drawBitmap(page, 0f, 0f, null)
//
//// Sử dụng Bitmap (ví dụ: hiển thị trong ImageView)
//        val imageView = findViewById<ImageView>(R.id.imageView2)
//
//        imageView.setImageBitmap(bitmap)
//
//// Đảm bảo đóng PDDocument khi hoàn tất
//        document.close()



        val accessToken = hasAccessToken(this.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
        Log.d("accessToken", accessToken.toString())



            val intent: Intent
            if (accessToken){
                intent=  Intent(this@MainActivity, HoSoBenhAnActivity::class.java)
            } else {
                intent= Intent(this@MainActivity, LoginActivity::class.java)
            }

            startActivity(intent)
//            delay(1000)
            finish()


        }

    }

//    private lateinit var webView: WebView
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.layout)
//
//        webView = findViewById(R.id.webView)
//        webView.webViewClient = WebViewClient()
//
//        // Kích hoạt JavaScript
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true
//
//        val pdfUrl = "https://pdfobject.com/pdf/sample.pdf"
//
//
//        Log.d("pdfUrl", pdfUrl)
//        Log.d("dang chay o day", "dang chay o day")
//        webView.loadUrl("file:///android_asset/web/viewer.html?file="+ pdfUrl)
////        webView.loadUrl("https://vnexpress.net")
//    }
}

