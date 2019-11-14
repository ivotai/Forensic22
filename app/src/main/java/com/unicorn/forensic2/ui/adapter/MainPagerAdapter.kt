package com.unicorn.forensic2.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.ui.HomeFra

@Suppress("DEPRECATION")
class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        const val size = 4
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFra()
        1 -> HomeFra()
        2 -> HomeFra()
        else -> HomeFra()
    }

    override fun getCount(): Int = size

}