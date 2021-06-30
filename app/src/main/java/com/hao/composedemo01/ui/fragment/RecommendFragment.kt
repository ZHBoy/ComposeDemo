package com.hao.composedemo01.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hao.composedemo01.theme.ComposeDemo01Theme
import com.hao.composedemo01.ui.LazyFragment
import com.hao.composedemo01.viewmodel.main.MainViewModel

class RecommendFragment : LazyFragment() {

    companion object {
        fun newInstance() = RecommendFragment()
    }

    override fun afterwardsVisible() {

    }

    override fun firstVisible() {
        MainViewModel.getInstance().updateHomeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeDemo01Theme {
                    Surface(color = MaterialTheme.colors.background) {
                        FragmentContentScreen(MainViewModel.getInstance()) {
                            firstVisible()
                        }
                    }
                }
            }
        }
    }
}


