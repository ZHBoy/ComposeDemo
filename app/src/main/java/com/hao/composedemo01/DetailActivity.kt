package com.hao.composedemo01

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.hao.composedemo01.data.UserInfoData
import com.hao.composedemo01.ui.theme.ComposeDemo01Theme
import com.hao.composedemo01.ui.theme.SetTopBar

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userInfoData: UserInfoData? = intent.getSerializableExtra("userInfo") as? UserInfoData
        setContent {
            HomeScreen(this, userInfoData)
        }
    }
}

@Composable
fun HomeScreen(context: Context, userInfoData: UserInfoData?) {
    return Scaffold(
        topBar = {
            SetTopBar(context = context, title = userInfoData?.name ?: "title=null")
        },
        content = {
            ComposeDemo01Theme {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = userInfoData?.name + " " + userInfoData?.password, fontSize = 20.sp)
                }
            }
        }
    )
}