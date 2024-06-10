package vn.bvntp.app.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.bvntp.app.MainActivity
import vn.bvntp.app.api.UserService
import vn.bvntp.app.model.UserLoginInfo
import vn.bvntp.app.network.RetrofitClient
import vn.bvntp.app.network.RetrofitClient.userService
import vn.bvntp.app.repository.UserRepository
import vn.bvntp.app.ui.activity.HoSoBenhAnActivity
import vn.bvntp.app.ui.activity.LoginActivity
import vn.bvntp.app.ui.component.CancleButton
import vn.bvntp.app.ui.component.LoginButton
import vn.bvntp.app.ui.component.LoginFailedMessage
import vn.bvntp.app.ui.component.LoginSuccess
import vn.bvntp.app.ui.component.LoginSuccessMessage
import vn.bvntp.app.ui.component.Logo
import vn.bvntp.app.viewmodel.LoginStatus


import vn.bvntp.app.viewmodel.LoginViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginView(context: Context,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
                  ) {

    val userState by viewModel.userInfo.observeAsState(UserLoginInfo("", ""),)
    val isLogin by viewModel.isLogin.observeAsState(LoginStatus.NotYetLogin)
    val activity = (LocalContext.current as Activity)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Logo(
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            TextField(
                value = userState.UserName,
                onValueChange = {
                    if (it.length <= 255) viewModel.updateUserInfo("username", it)
                },
                placeholder = { Text(text = "Nhập tên đăng nhập") },
                label = { Text(text = "Tên đăng nhập") },
                trailingIcon = {
                    if (userState.UserName.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateUserInfo("username", "") }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }, singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = userState.Password,
                onValueChange = {
                    if (it.length <= 255) viewModel.updateUserInfo("password", it)
                },
                placeholder = { Text(text = "Nhập mật khẩu") },
                label = { Text(text = "Mật khẩu") },
                trailingIcon = {
                    if (userState.Password.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateUserInfo("password", "") }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }, singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            LoginButton(
                onClick = { viewModel.viewLogin(context = context) },
                modifier = Modifier.fillMaxWidth()
            )
            CancleButton(onClick = {activity.finish()}, modifier = Modifier.fillMaxWidth())

            if (isLogin == LoginStatus.Failed) {
                LoginFailedMessage(context = context, viewModel = viewModel)
                viewModel.updateIsLogin(LoginStatus.NotYetLogin)
            } else if (isLogin == LoginStatus.Success) {
                LoginSuccessMessage(context = context, viewModel = viewModel)
                viewModel.updateIsLogin(LoginStatus.NotYetLogin)
                val hoSoBenhAn = Intent(context, HoSoBenhAnActivity::class.java)
                activity.finish()
                context.startActivity(hoSoBenhAn)
            } else {
                viewModel.updateIsLogin(LoginStatus.NotYetLogin)
            }
        }
    }

}
