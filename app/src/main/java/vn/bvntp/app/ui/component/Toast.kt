package vn.bvntp.app.ui.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import vn.bvntp.app.viewmodel.LoginStatus
import vn.bvntp.app.viewmodel.LoginViewModel

@Composable
fun LoginSuccessMessage(context: Context, viewModel: LoginViewModel){
    val loginMessage by viewModel.loginMessage.observeAsState(LoginStatus.NotYetLogin)
    Toast.makeText(context, "Đăng nhập $loginMessage", Toast.LENGTH_SHORT ).show()
}

@Composable
fun LoginFailedMessage(context: Context, viewModel: LoginViewModel){
    val loginMessage by viewModel.loginMessage.observeAsState(LoginStatus.NotYetLogin)
    Toast.makeText(context, "Đăng nhập $loginMessage", Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun LoginSuccess(){
    LoginSuccess()
}