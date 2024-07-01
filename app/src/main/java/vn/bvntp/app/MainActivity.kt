package vn.bvntp.app

//import com.tom_roush.pdfbox.pdmodel.PDDocument
//import com.tom_roush.pdfbox.rendering.PDFRenderer

import android.content.Intent
import android.os.Bundle
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.hasAccessToken
import vn.bvntp.app.ui.activity.HostActivity
import vn.bvntp.app.ui.component.Logo

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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
                            .size(300.dp, 300.dp)
                        )
                    }

                }
            }
        }

        val accessToken = hasAccessToken(this.applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            val intent: Intent
            intent=  Intent(this@MainActivity, HostActivity::class.java)
//            if (accessToken){
//                intent=  Intent(this@MainActivity, HoSoBenhAnActivity::class.java)
//            } else {
//                intent= Intent(this@MainActivity, LoginActivity::class.java)
//            }
            startActivity(intent)
            finish()
        }
    }

}

