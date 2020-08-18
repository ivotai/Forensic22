package com.unicorn.forensic2.refactor.tztx

import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_tztx.*

class TztxAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("通知提醒")

        val pagerAdapter = TztxPagerAdapter(fm = supportFragmentManager)
        viewPaper.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPaper)
    }

    override val layoutId = R.layout.act_tztx

}