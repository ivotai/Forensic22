package com.unicorn.forensic2.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.ui.fra.HomeMainFra
import com.unicorn.forensic2.ui.fra.CaseListFra
import com.unicorn.forensic2.ui.fra.MyMainFra
import com.unicorn.forensic2.ui.fra.WdpsFra

@Suppress("DEPRECATION")
class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles: List<String> get() = if (!isLogin) listOf("首页") else listOf("首页", "案件/评审", "我的")
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeMainFra()
        1 -> if (roleTag == Role.Pszj.en) WdpsFra() else CaseListFra()
        else -> MyMainFra()
    }

    override fun getCount(): Int = titles.size

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}