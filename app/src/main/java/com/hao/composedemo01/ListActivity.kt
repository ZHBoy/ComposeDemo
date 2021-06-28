package com.hao.composedemo01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hao.composedemo01.data.UserInfoData
import com.hao.composedemo01.ui.theme.ComposeDemo01Theme
import com.hao.composedemo01.ui.theme.SetTopBar

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = ArrayList<UserInfoData>()
        for (i in 0..100) {
            list.add(UserInfoData(i, "xx第${i}名", "password:${i}"))
        }
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
    val intent = Intent(context, DetailActivity::class.java)

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
                    Text(userInfo.name, modifier = Modifier
                        .padding(all = 12.dp)
                        .clickable {
                            intent.putExtra("userInfo", userInfo)
                            context.startActivity(intent)
                        })
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

