package com.hao.composedemo01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.viewpager2.widget.ViewPager2

import com.hao.composedemo01.adapter.ViewPagerAdapter
import com.hao.composedemo01.ui.fragment.HomeFragment
import com.hao.composedemo01.ui.fragment.RecommendFragment
import com.hao.composedemo01.ui.fragment.SquareFragment
import com.hao.composedemo01.ui.fragment.UserFragment
import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    private var bottomNaviView: BottomNavigationView? = null
    private var myViewPager2: ViewPager2? = null
    private var myAdapter: ViewPagerAdapter? = null

    private val map = mutableMapOf<MenuItem, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNaviView = findViewById(R.id.bottom_navi_view)

        myViewPager2 = findViewById(R.id.main_vp2)

        val fragmentList = arrayListOf(
            HomeFragment.newInstance(),
            RecommendFragment.newInstance(),
            SquareFragment.newInstance(),
            UserFragment.newInstance()
        )

        //建立MenuItem的索引id映射
        bottomNaviView!!.menu.forEachIndexed { index, item ->
            map[item] = index
        }

        myAdapter = ViewPagerAdapter(this, fragmentList)

        myViewPager2?.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = myAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val item: MenuItem = bottomNaviView!!.menu.getItem(position)
                    item.isChecked = true
                }
            })
        }

//        //设置一个侦听器，该侦听器在选择底部导航项时将得到通知。除非还设置了{@link OnNavigationItemReselectedListener}，否则当重新选择当前选择的项目时，也会通知此侦听器。
        bottomNaviView?.setOnNavigationItemSelectedListener { item ->
            myViewPager2?.setCurrentItem(
                map[item] ?: error("BottomNavigationView的Item${item.itemId}没有对应的元素"), false
            )
            true
        }
    }

}
