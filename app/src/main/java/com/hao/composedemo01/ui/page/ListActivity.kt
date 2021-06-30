package com.hao.composedemo01.ui.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.hao.composedemo01.data.UserInfoData
import com.hao.composedemo01.theme.ComposeDemo01Theme
import com.hao.composedemo01.theme.SetTopBar
import com.hao.composedemo01.theme.Shapes

/**
 * 列表页
 */
class ListActivity : AppCompatActivity() {

    private val list = ArrayList<UserInfoData>()

    init {
        for (i in 0..100) {
            //"https://picsum.photos/300/300"
            val url: String = when {
                i / 3 == 0 -> "https://i.picsum.photos/id/427/300/300.jpg?hmac=JYcuQURhrFc6zdLf_J6ItUzSDGT9YX0BZ1iGO1tezFg"
                i / 2 == 0 -> "https://i.picsum.photos/id/986/300/300.jpg?hmac=KcuGkESIv-R4hMb6oNLnR-ZLHKydNYeI7cz-fsI2LhM"
                i / 5 == 0 -> "https://i.picsum.photos/id/151/300/300.jpg?hmac=FR0Jk2HWycIhDqxBYgljYeOmZokAiIe9tMGCvrk-IhY"
                i / 7 == 0 -> "https://i.picsum.photos/id/1035/300/300.jpg?hmac=h2e6yb4s09DR32Lvxopvsee73kUjJIpGLxp0IpxxN2c"
                else -> {
                    "https://i.picsum.photos/id/730/300/300.jpg?hmac=-ZzQFNagVnAQRxKCAdznDnNils2eMzvfT7ooODMz1ws"
                }
            }
            list.add(UserInfoData(i, "xx第${i}名", "password:${i}", url))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemo01Theme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen(this, list)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(context: Context, userDataList: ArrayList<UserInfoData>) {
    return Scaffold(
        topBar = {
            SetTopBar(context = context, title = "列表页")
        },
        content = {
            LazyColumn {
                items(
                    items = userDataList,
                    key = { userInfo ->
                        // Return a stable + unique key for the item
                        userInfo.id
                    }
                ) { userInfo ->
                    ArtistCard(userInfo = userInfo) {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("userInfo", userInfo)
                        context.startActivity(intent)
                    }
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .background(Color.Gray)
                    )
                }
            }
        }
    )
}

/**
 * 列表条目
 */
@Composable
fun ArtistCard(userInfo: UserInfoData, click: (() -> Unit)) {
    val padding = 16.dp
    Column(
        Modifier
            .clickable(onClick = click)
            .padding(padding)
            .fillMaxWidth()
            .border(
                BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                shape = Shapes.large
            )
            .padding(padding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                painter = rememberCoilPainter(
                    request = userInfo.url,
                    requestBuilder = {
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(userInfo.name, fontSize = 16.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(10.dp))
                Text(userInfo.name, fontSize = 12.sp, color = Color.Gray)
            }

        }
        Spacer(Modifier.size(padding))
        Card(elevation = 4.dp) {
            Image(
                painter = rememberCoilPainter(
                    request = userInfo.url
                ),
                contentDescription = null,
            )
        }
    }

}
