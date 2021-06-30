package com.hao.composedemo01.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hao.composedemo01.ui.LazyFragment

class ViewPagerAdapter(fa: FragmentActivity, private val fragments: ArrayList<LazyFragment>) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}