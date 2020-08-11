package com.unicorn.forensic2.refactor.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.case1.CaseFra
import com.unicorn.forensic2.refactor.main.home.HomeFra
import com.unicorn.forensic2.refactor.main.profile.ProfileFra
import com.unicorn.forensic2.ui.fra.WdpsFra

class MainPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFra()
        1 -> if (role == Role.Pszj) WdpsFra() else CaseFra()
        else -> ProfileFra()
    }

    override fun getCount(): Int = if (isLogin) 3 else 1

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}