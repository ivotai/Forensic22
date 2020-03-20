package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class CaseDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("案件详情")
    }

    override fun bindIntent() {
        v1Api.getCaseProcessList(caseId = case.caseId, page = 1)
            .observeOnMain(this)
            .subscribe()
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.ui_title_recycler

}