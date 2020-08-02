package com.unicorn.forensic2.ui.fra

import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_wdps.*

class WdpsFra : BaseFra() {

    override fun initViews() {
        titleBar.setTitle("我的评审", false)

        val psxxPagerAdapter = PsxxPagerAdapter(fm = childFragmentManager)
        viewPaper.adapter = psxxPagerAdapter
        tabLayout.setupWithViewPager(viewPaper)
    }

    override val layoutId = R.layout.fra_wdps

}