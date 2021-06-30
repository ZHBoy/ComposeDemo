package com.hao.composedemo01.ui

import android.util.Log
import androidx.fragment.app.Fragment

/**
 * 懒加载的fragment
 */
abstract class LazyFragment : Fragment() {

    private var isFirstLoad = true

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            isFirstLoad = false
            firstVisible()
            Log.d("LazyFragment", "LazyFragment:   ${javaClass.simpleName}")
        } else {
            afterwardsVisible()
        }
    }

    /**
     * 页面再次展示
     */
    abstract fun afterwardsVisible()

    /**
     * 页面第一次展示
     */
    abstract fun firstVisible()
}