package vn.bvntp.app.ui.activity

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.example.ntp_app.ui.theme.AppNTPTheme
import dagger.hilt.android.AndroidEntryPoint
import vn.bvntp.app.App
import vn.bvntp.app.helper.hasAccessToken
import vn.bvntp.app.model.UserLoginInfo
import vn.bvntp.app.network.RetrofitClient
import vn.bvntp.app.repository.UserRepository
import vn.bvntp.app.ui.view.LoginView
import vn.bvntp.app.viewmodel.LoginViewModel
import vn.bvntp.app.viewmodel.LoginViewModelFactory
import javax.inject.Inject



class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        val appContainer = (applicationContext as App).container
        val viewModel =  ViewModelProvider(this, appContainer.viewModelFactory).get(LoginViewModel::class.java)

        setContent {
            AppNTPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LoginView(context=context, viewModel = viewModel  ,modifier = Modifier.fillMaxSize())

                }

            }
        }
    }
}
