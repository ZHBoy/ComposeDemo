package com.hao.composedemo01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hao.composedemo01.ui.page.ListActivity
import com.hao.composedemo01.theme.ComposeDemo01Theme

class MainActivity : ComponentActivity() {
    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemo01Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(this)
                }
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
fun Greeting(context: Context) {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
    ) { // 添加Column，使布局垂直排列
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("我")
                }
                append("是一个")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("拼接")
                }
                append("文本")
            },
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(all = Dp(10F))
                .border(2.dp, MaterialTheme.colors.secondary, CircleShape)
                .background(Color.Gray, CircleShape)
                .padding(all = 20.dp)
                .clickable {
                    println("我是一个拼接的文本")
                })
        Spacer(modifier = Modifier.height(20.dp))

        ScrollableTabRow()

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, ListActivity::class.java))
            },
            modifier = Modifier.padding(all = Dp(10F)),
            enabled = true,
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.medium,
        )
        {
            Text(text = "去列表页", color = Color.White)
        }
    }
}

@Composable
@ExperimentalUnitApi
@Preview(showBackground = true)
fun ScrollableTabRow() {
    var state = remember { mutableStateOf(0) }
    val titles = listOf("标签1", "标签2", "标签3", "标签4", "标签4", "标签5", "标签6", "标签7")
    Column {
        ScrollableTabRow(
            selectedTabIndex = state.value,
            modifier = Modifier.wrapContentWidth(),
            edgePadding = 16.dp
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state.value == index,
                    onClick = { state.value = index }
                )
            }
        }

        Text(
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "第${state.value + 1}个标签被选中了",
            style = MaterialTheme.typography.body1
        )
    }
}