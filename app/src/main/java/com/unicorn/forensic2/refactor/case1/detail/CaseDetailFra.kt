package com.unicorn.forensic2.refactor.case1.detail

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case_detail.*

class CaseDetailFra : BaseFra() {

    override fun initViews() {
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

    private val case by lazy { arguments?.getSerializable(Param) as Case }

    override val layoutId = R.layout.fra_case_detail

}