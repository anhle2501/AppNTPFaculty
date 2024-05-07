package vn.bvntp.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.ntp_app.ui.theme.AppNTPTheme

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.hasAccessToken
import vn.bvntp.app.network.RetrofitClient
import vn.bvntp.app.repository.UserRepository
import vn.bvntp.app.ui.activity.HoSoBenhAnActivity
import vn.bvntp.app.ui.activity.LoginActivity
import vn.bvntp.app.ui.activity.PdfViewerActivity
import vn.bvntp.app.ui.component.CancleButton
import vn.bvntp.app.ui.component.Logo
import vn.bvntp.app.viewmodel.LoginViewModel
import vn.bvntp.app.viewmodel.LoginViewModelFactory
import java.lang.Exception
import kotlin.concurrent.thread

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
//                        .fillMaxSize()
                            .size(300.dp, 300.dp)
                        )
                    }

                }
            }
        }

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
}

