package com.hao.composedemo01.viewmodel.main

import androidx.lifecycle.*
import com.hao.composedemo01.data.UserInfoData
import com.hao.composedemo01.viewmodel.AppStoreOwner
import com.hao.composedemo01.viewmodel.SafetyLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    companion object {
        private val vp: ViewModelProvider by lazy {
            ViewModelProvider(AppStoreOwner, ViewModelProvider.NewInstanceFactory())
        }

        fun getInstance(key: String = "main") = vp.get(key, MainViewModel::class.java)
    }

    val homeViewModel = SafetyLiveData<UserInfoData>()

    /**
     * 更新数据
     */
    fun updateHomeData() {
        viewModelScope.launch {
            homeViewModel.value = withContext(Dispatchers.IO) {
                //做网络请求或者读取数据库
                val number = (100..100000).random()
                UserInfoData(number, "name: $number", "password: $number", "")
            }
        }
    }

}
