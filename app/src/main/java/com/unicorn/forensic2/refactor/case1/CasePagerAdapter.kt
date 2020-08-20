package com.unicorn.forensic2.refactor.case1

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.CaseType

class CasePagerAdapter(fm: FragmentManager) : com.unicorn.forensic2.refactor.other.FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getItem(position: Int): Fragment = CaseListFra().apply {
        val bundle = Bundle()
        bundle.putSerializable(Param, CaseType.all[position])
        this.arguments = bundle
        retainInstance = false
    }

    override fun getCount(): Int = CaseType.all.size

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}