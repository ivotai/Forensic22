package com.unicorn.forensic2.refactor.case.detail

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_case_detail.*

class CaseDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("案件详情")

        with(case) {
            tvJdNo.text = jdNo
            tvCourtNameApply.text = courtNameApply
            tvCaseType.text = caseTypeX
            tvCaseStatus.text = caseStatusX
            tvCaseNo.text = caseNo
            tvCourtNameAccept.text = courtNameAccept
            tvDateAccept.text = dateAccept.toDisplayFormat2()
            tvDateClose.text = dateClose.toDisplayFormat2("尚未结案")
            tvCloseTypeDisplay.text = if (closeTypeDisplay.isEmpty()) "尚未结案" else closeTypeDisplay
        }
    }

    override val layoutId = R.layout.act_case_detail

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}