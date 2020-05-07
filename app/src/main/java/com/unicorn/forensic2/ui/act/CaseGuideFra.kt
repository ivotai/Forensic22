package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.model.JgCaseType
import com.unicorn.forensic2.ui.base.BaseFra
import com.unicorn.forensic2.ui.fra.DsrCaseAct
import com.unicorn.forensic2.ui.fra.JgCaseAct
import com.unicorn.forensic2.ui.fra.ZjCaseAct
import kotlinx.android.synthetic.main.fra_case_guide.*

class CaseGuideFra : BaseFra() {

    override fun initViews() {
        titleBar.setTitle("案件", false)
    }

    override fun bindIntent() {
        button.safeClicks().subscribe {
            Intent(context!!, JgCaseAct::class.java).apply {
                putExtra(Param, JgCaseType.ZBTZ)
            }.let { context!!.startActivity(it) }
        }
        button2.safeClicks().subscribe { context!!.startAct(ZjCaseAct::class.java) }
        button3.safeClicks().subscribe { context!!.startAct(DsrCaseAct::class.java) }
    }

    override val layoutId = R.layout.fra_case_guide

}