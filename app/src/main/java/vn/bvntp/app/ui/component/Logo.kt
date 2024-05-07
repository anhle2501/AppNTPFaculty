package vn.bvntp.app.ui.component

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.bvntp.app.R

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = R.drawable.ntp_logo),
        modifier = modifier,
        contentDescription = stringResource(R.string.bvntp))

}

@Preview
@Composable
fun PreviewLogo(){
    Logo()
}
