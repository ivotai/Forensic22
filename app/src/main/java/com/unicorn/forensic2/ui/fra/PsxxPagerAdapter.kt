package com.unicorn.forensic2.ui.fra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

@Suppress("DEPRECATION")
class PsxxPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        val titles: List<String> get() = listOf("待评审", "已评审")
    }

    override fun getItem(position: Int): Fragment =
        if (position == 0) JdReviewDpsListFra() else JdReviewYwcListFra()

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}