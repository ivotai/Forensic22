package com.unicorn.forensic2.ui.act

import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_tsjy.*

class TsjyAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("投诉建议")
    }

    override fun bindIntent() {
        ivYhf.clicks().mergeWith(tvYhf.clicks()).subscribe { startAct(TsjyYhfListAct::class.java) }
        ivWhf.clicks().mergeWith(tvWhf.clicks()).subscribe { startAct(TsjyWhfListAct::class.java) }
    }

    override val layoutId = R.layout.act_tsjy

}