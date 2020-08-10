package com.unicorn.forensic2.refactor.case1.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.refactor.case1.detail.process.CaseProcessListFra

@Suppress("DEPRECATION")
class CaseDetailPagerAdapter(fm: FragmentManager, val case: Case) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fra = when (position) {
            0 -> CaseDetailFra()
            1 -> CaseProcessListFra()
            else -> CaseProcessListFra()
        }
        return fra.apply {
            val bundle = Bundle()
            bundle.putSerializable(Param, case)
            this.arguments = bundle
        }
    }


    override fun getCount(): Int = CaseDetailType.all.size

}