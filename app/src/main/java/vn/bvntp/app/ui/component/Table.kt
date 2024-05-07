package vn.bvntp.app.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.bvntp.app.model.LichSuDieuTriResponse

@Composable
fun Table(
//    data: LichSuDieuTriResponse, modifier: Modifier
){
    // Header

    var modifierCell = Modifier
        .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
        .padding(5.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .border(border =  BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary))
//            .padding(2.dp)
    ) {
        Row(modifier = Modifier
            .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
            .defaultMinSize(minWidth = 5.dp, minHeight = 5.dp)
        ){
            Text(text = "Khoa", modifier = Modifier
                .weight(1f)
//                .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
                .padding(5.dp)
            )
            Text(text = "Ngày vào", modifier = Modifier
                .weight(1f)

                .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
                .padding(5.dp)
            )
            Text(text = "Ngày ra", modifier = Modifier
                .weight(1f)

                .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
                .padding(5.dp)
            )
            Text(text = "Trạng thái", modifier = Modifier
                .weight(1f)
                .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
                .padding(5.dp)
            )
        }
        Row {
            Text(text = "Đợt vào viện",  modifier = Modifier
                .weight(1f)
                .border(border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary))
                .padding(5.dp)
            )
        }
        Row {


            Text(
                text = "PK (A.08B1) nội tổng quát", modifier = Modifier
                    .weight(0.45f)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    .padding(5.dp)
            )
            Text(
                text = "N16/04/2024 08:34", modifier = Modifier
                    .weight(0.45f)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    .padding(5.dp)
            )

            Text(
                text = "N16/04/2024 08:34", modifier = Modifier
                    .weight(0.45f)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    .padding(5.dp)
            )

            Text(
                text = "Chuyển phòng khám", modifier = Modifier
                    .weight(0.45f)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    .padding(5.dp)
            )
        }


    }
}
//
//@Composable
//fun Table2(){
//    val numbers = (0..20).toList()
//
//    LazyColumn {
//        item(2) {
//            LazyVerticalGrid(
//                columns = GridCells.Fixed(4)
//            ) {
//                items(numbers.size) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text(text = "Number")
//                        Text(text = "  $it",)
//                    }
//                }
//            }
//
//        }
//
//    }
//
//}

@Preview
@Composable
fun TablePreview(){
    Table(

    )
//    Table2()
//    Demo_Table()
}
