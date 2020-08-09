package com.unicorn.forensic2.refactor.case

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.CaseType

@Suppress("DEPRECATION")
class CasePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

//    companion object {
////        val titles: List<String> get() = listOf("", "已读")
////    }

    override fun getItem(position: Int): Fragment = CaseListFra().apply {
        val bundle = Bundle()
        bundle.putSerializable(Param, CaseType.all[position])
        this.arguments = bundle
    }

    override fun getCount(): Int = CaseType.all.size

//    override fun getPageTitle(position: Int): CharSequence? {
//        return titles[position]
//    }

}