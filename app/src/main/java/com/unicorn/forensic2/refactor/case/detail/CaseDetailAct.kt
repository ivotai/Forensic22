package com.unicorn.forensic2.refactor.case.detail

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_case_detail.*

class CaseDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("案件详情")
    }

    override val layoutId = R.layout.act_case_detail

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}