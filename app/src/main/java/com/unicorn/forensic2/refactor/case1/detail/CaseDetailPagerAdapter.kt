package com.unicorn.forensic2.refactor.case1.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Case

@Suppress("DEPRECATION")
class CaseDetailPagerAdapter(fm: FragmentManager, val case: Case
) : FragmentStatePagerAdapter(fm) {

//    companion object {
////        val titles: List<String> get() = listOf("", "已读")
////    }

    override fun getItem(position: Int): Fragment = CaseDetailFra().apply {
        val bundle = Bundle()
        bundle.putSerializable(Param, case)
        this.arguments = bundle
    }

    override fun getCount(): Int = CaseDetailType.all.size

//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles[position]
//    }

}