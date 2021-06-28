package com.hao.composedemo01

import android.os.Message
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.paging.Pager
import com.hao.composedemo01.data.UserInfoData

object ViewObject {

    @Composable
    fun MessageList(pager: Pager<Int, Message>) {
        val lazyPagingItems = pager.flow

//        LazyColumn {
//            items(lazyPagingItems) { message ->
//                if (message != null) {
//                    MessageRow(message)
//                } else {
//                    MessagePlaceholder()
//                }
//            }
//        }
    }


}