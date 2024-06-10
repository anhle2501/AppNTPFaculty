package vn.bvntp.app.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.ntp_app.ui.theme.AppNTPTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import vn.bvntp.app.App
import vn.bvntp.app.ui.view.HoSoBenhAnView
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel

class HoSoBenhAnActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (applicationContext as App).container
        val viewModel =  ViewModelProvider(this, appContainer.hsbaViewModelFactory).get(
            HoSoBenhAnViewModel::class.java)
        var barcodeLauncher: ActivityResultLauncher<ScanOptions> = registerForActivityResult(
            ScanContract()
        ) { result ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                viewModel.updateMaBenhNhan(result.contents.toString())
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                    .show()
            }
        }
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
        options.setPrompt("Quét mã bệnh nhân")
        options.setCameraId(0)  // Use a specific camera of the device
        options.setBeepEnabled(true)
        options.setBarcodeImageEnabled(true)
        val barcodeLauncherWithOption = {  barcodeLauncher.launch(options) }

        setContent {
            AppNTPTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                        HoSoBenhAnView(LocalContext.current, viewModel, barcodeLauncherWithOption)
                }
            }
        }
    }
}


