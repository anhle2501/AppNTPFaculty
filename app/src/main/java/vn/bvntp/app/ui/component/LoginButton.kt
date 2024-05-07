package vn.bvntp.app.ui.component

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ntp_app.backgroundLight
import vn.bvntp.app.viewmodel.LoginViewModel



@Composable
fun LoginButton(onClick: () -> Unit, modifier: Modifier = Modifier){

    val context = LocalContext.current
    val viewModel =

    Button(onClick = onClick,
        modifier = modifier
        ) {
        Text(text = "Đăng nhập")
    }


}

@Preview
@Composable
fun PreviewLoginButton(){
    LoginButton({})
}


@Composable
fun CancleButton(onClick: () -> Unit,modifier: Modifier = Modifier){
    OutlinedButton(onClick = onClick,
        modifier = modifier) {
        Text(text = "Thoát")
    }
}
@Preview
@Composable
fun PreviewCancleButton(){
    CancleButton({})
}
