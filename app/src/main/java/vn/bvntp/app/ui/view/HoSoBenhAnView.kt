package vn.bvntp.app.ui.view


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntp_app.primaryLight
import vn.bvntp.app.helper.storeEncryptedData
import vn.bvntp.app.ui.activity.LoginActivity
import vn.bvntp.app.ui.activity.PdfViewer
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoSoBenhAnView(
    context: Context, hoSoBenhAnViewModel: HoSoBenhAnViewModel, barcodeLauncher: () -> Unit
) {
    val activity = (LocalContext.current as Activity)
    Column(
        modifier = Modifier.padding(40.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    storeEncryptedData(context, "access_token", "")
                    val intent = Intent(
                        context, LoginActivity::class.java
                    )
                    activity.finish()
                    context.startActivity(intent)
                }, modifier = Modifier.scale(0.8f), enabled = true
            ) {
                Text(text = "Thoát tài khoản", fontSize = 12.sp)
            }
        }

        Text(text = "Hồ sơ bệnh án", textAlign = TextAlign.Center, fontSize = 30.sp)

        val maBenhNhan by hoSoBenhAnViewModel.maBenhNhan.observeAsState(initial = "")
        val listId by hoSoBenhAnViewModel.listId.observeAsState(initial = ArrayList())
        var active by remember {
            mutableStateOf(false)
        }
        val listLichSuDieuTri by hoSoBenhAnViewModel.lichSuDieuTri.observeAsState(initial = emptyList())
        val isLock by hoSoBenhAnViewModel.isLock.observeAsState(initial = false)
        val focusManager = LocalFocusManager.current
        val isLockList by hoSoBenhAnViewModel.isLockList.observeAsState(initial = false)


        SearchBar(modifier = Modifier.fillMaxWidth(), query = maBenhNhan, onQueryChange = {
            hoSoBenhAnViewModel.updateMaBenhNhan(it)
        }, onSearch = {
//                active = false
        }, active = active, onActiveChange = {
//                active = it
        }, placeholder = { "Nhập mã bệnh nhân" }, leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "Search icon"
            )
        }, trailingIcon = {

            Row {
                Icon(
                    painter = painterResource(id = vn.bvntp.app.R.drawable.photo_camera),
                    contentDescription = "Vui lòng quét mã vạch", // decorative element
                    tint = primaryLight,
                    modifier = Modifier.padding(14.dp).clickable {
                            barcodeLauncher.invoke()
                        },
                    )
                if (maBenhNhan != "") {
                    Icon(
                        modifier = Modifier.padding(16.dp).clickable {
                                if (maBenhNhan.isNotEmpty()) {
                                    hoSoBenhAnViewModel.updateMaBenhNhan("")
                                } else {
                                    active = false
                                }

                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close icon"
                    )
                }
            }
        }) {}
        Button(
            onClick = {
                if (vn.bvntp.app.helper.Validator.isMaBenhNhanValid(maBenhNhan)) {
                    hoSoBenhAnViewModel.toggleIsLock()
                    hoSoBenhAnViewModel.updateMaBenhNhan(maBenhNhan)
                    hoSoBenhAnViewModel.modelLichSuVaoVienView(context)
                    focusManager.clearFocus()

                } else {
                    Toast.makeText(
                        context, "Nhập mã bệnh nhân gồm 8 ký tự số !", Toast.LENGTH_SHORT
                    ).show()
                }
            }, modifier = Modifier.fillMaxWidth(), enabled = !isLock
        ) {
            Text(text = "Tìm kiếm", fontSize = 20.sp)
        }

        if (isLockList) {
            Column {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                Button(
                    onClick = {
                        hoSoBenhAnViewModel.toggleIsLockList()
                        hoSoBenhAnViewModel.toggleIsLock()
                    }, modifier = Modifier.padding(10.dp), enabled = true
                ) {
                    Text(text = "Hủy", fontSize = 20.sp)
                }
            }

        } else {
            if (listLichSuDieuTri.isNotEmpty()) {
                Text(
                    text = "Vui lòng chọn ngày vào viện để xem hồ sơ bệnh án",
                    Modifier.align(Alignment.CenterHorizontally)
                )
                LazyColumn(
                    contentPadding = PaddingValues(5.dp, 5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    itemsIndexed(items = listLichSuDieuTri) { index, item ->
                        val namVaoVien = listLichSuDieuTri[index].substring(
                            startIndex = 0, endIndex = 2
                        )
                        val thangVaoVien = listLichSuDieuTri[index].substring(
                            startIndex = 2, endIndex = 4
                        )
                        val ngayVaoVien = listLichSuDieuTri[index].substring(
                            startIndex = 4, endIndex = 6
                        )
                        ListItem(
                            tonalElevation = 1.dp, shadowElevation = 1.dp, headlineContent = {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Ngày vào viện: $ngayVaoVien / $thangVaoVien / 20$namVaoVien"
                                )
                            }, modifier = Modifier.padding(10.dp).clickable {
                                    hoSoBenhAnViewModel.setMaVaoVienVaListId(
                                        maVaoVien = listLichSuDieuTri[index], listId = listId
                                    )
                                    hoSoBenhAnViewModel.toggleIsLockList()
                                    hoSoBenhAnViewModel.toggleIsLock()
                                    hoSoBenhAnViewModel.modelHoSoBenhAnView(
                                        context
                                    ) {
                                        val intent = Intent(
                                            context, PdfViewer::class.java
                                        )
                                        intent.putExtra(
                                            "fileUri", hoSoBenhAnViewModel.getTemp()
                                        )
                                        hoSoBenhAnViewModel.toggleIsLockList()
                                        hoSoBenhAnViewModel.toggleIsLock()
                                        context.startActivity(intent)
                                    }
                                })
                    }
                }
            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun namBenhAnIcon(nam: String) {
    Badge(
        containerColor = Color.Blue
    ) {
        Text(
            fontSize = 15.sp, color = Color.White, text = nam.toString()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TimKiemPre() {

    Column(
        modifier = Modifier.padding(40.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {

                }, modifier = Modifier
//                    .size(width = 150.dp, height = 50.dp)
                    .scale(0.8f), enabled = true
            ) {
                Text(text = "Thoát tài khoản", fontSize = 12.sp)
            }
        }


        Text(text = "Hồ sơ bệnh án", textAlign = TextAlign.Center, fontSize = 30.sp)

        SearchBar(modifier = Modifier.fillMaxWidth().padding(10.dp),
            query = null.toString(),
            onQueryChange = {},
            onSearch = {
//                active = false
            },
            active = false,
            onActiveChange = {
//                active = it
            },
            placeholder = { "Nhập mã bệnh nhân" },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search icon"
                )
            },
            trailingIcon = {

                Row {
                    Icon(
                        painter = painterResource(id = vn.bvntp.app.R.drawable.photo_camera),
                        contentDescription = "Take picture", // decorative element
                        tint = primaryLight,
                        modifier = Modifier.padding(14.dp).clickable {},

                        )

                }
            }) {}
        Button(
            onClick = {}, modifier = Modifier.fillMaxWidth().padding(10.dp), enabled = true
        ) {
            Text(text = "Tìm kiếm", fontSize = 20.sp)
        }

        Button(
            onClick = {}, modifier = Modifier.padding(10.dp), enabled = true
        ) {
            Text(text = "Hủy", fontSize = 20.sp)
        }
    }
}