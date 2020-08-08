package com.unicorn.forensic2.refactor.case

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.fra.CaseListFra

@Suppress("DEPRECATION")
class CasePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

//    companion object {
////        val titles: List<String> get() = listOf("", "已读")
////    }

    override fun getItem(position: Int): Fragment =
        CaseListFra()

    override fun getCount(): Int = CaseType.all.size

//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles[position]
//    }

}