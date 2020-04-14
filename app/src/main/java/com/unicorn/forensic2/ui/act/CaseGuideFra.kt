package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.ui.base.BaseFra
import com.unicorn.forensic2.ui.fra.JgCaseAct
import com.unicorn.forensic2.ui.fra.ZjCaseAct
import kotlinx.android.synthetic.main.fra_case_guide.*

class CaseGuideFra : BaseFra() {

    override val layoutId = R.layout.fra_case_guide

    override fun bindIntent() {
        button.safeClicks().subscribe { context!!.startAct(JgCaseAct::class.java) }
        button2.safeClicks().subscribe { context!!.startAct(ZjCaseAct::class.java) }
    }

}