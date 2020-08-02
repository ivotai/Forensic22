package com.unicorn.forensic2.ui.act

import android.content.Intent
import android.view.View
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.model.JdjgAdminCaseType
import com.unicorn.forensic2.ui.base.BaseFra
import com.unicorn.forensic2.ui.fra.DsrCaseAct
import com.unicorn.forensic2.ui.fra.JgCaseAct
import com.unicorn.forensic2.ui.fra.ZjCaseAct
import kotlinx.android.synthetic.main.fra_case_guide.*

class CaseGuideFra : BaseFra() {

    override fun initViews() {
        titleBar.setTitle("案件", false)

        with(user){
            if (!JdjgAdmin) button.visibility = View.GONE
            if (!Pszj) button2.visibility = View.GONE
            if (!Normal) button3.visibility = View.GONE
        }
    }

    override fun bindIntent() {
        button.safeClicks().subscribe {
            Intent(context!!, JgCaseAct::class.java).apply {
                putExtra(Param, JdjgAdminCaseType.ZBTZ)
            }.let { context!!.startActivity(it) }
        }
        button2.safeClicks().subscribe { context!!.startAct(ZjCaseAct::class.java) }
        button3.safeClicks().subscribe { context!!.startAct(DsrCaseAct::class.java) }
    }

    override val layoutId = R.layout.fra_case_guide

}