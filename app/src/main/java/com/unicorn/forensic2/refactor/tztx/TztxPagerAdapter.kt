package com.unicorn.forensic2.refactor.tztx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

@Suppress("DEPRECATION")
class TztxPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles: List<String> get() = listOf("未读", "已读")
    }

    override fun getItem(position: Int): Fragment =
        if (position == 0) NewTztxListFra() else OldTztxListFra()

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}