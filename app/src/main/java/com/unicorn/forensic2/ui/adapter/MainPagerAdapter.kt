package com.unicorn.forensic2.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.ui.HomeFra

@Suppress("DEPRECATION")
class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles: List<String> get() = if (!isLogin) listOf("首页") else listOf("首页", "案件", "我的")
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFra()
        1 -> HomeFra()
        else -> HomeFra()
    }

    override fun getCount(): Int = titles.size

}