package com.unicorn.forensic2.ui.fra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.ui.act.CaseGuideFra
import com.unicorn.forensic2.ui.fra.HomeMainFra
import com.unicorn.forensic2.ui.fra.JdjgAdminCaseListFra
import com.unicorn.forensic2.ui.fra.MyMainFra
import com.unicorn.forensic2.ui.fra.WdpsFra

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