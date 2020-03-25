package com.unicorn.forensic2.ui.other

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Case
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_case_detail.view.*

class CaseDetailHeader(context: Context, case: Case) : FrameLayout(context),
    LayoutContainer {

    override val containerView = this

    init {
        LayoutInflater.from(context).inflate(R.layout.header_case_detail, this, true)
        with(case) {
            tvJdNo.text = jdNo
            tvCaseNo.text = caseNo
            tvCaseStatus.text = caseStatus
            tvDateApply.text = dateApply.toDisplayFormat()
            tvPlanFinish.text = planFinish.toDisplayFormat()
            tvCloseTypeDisplay.text = closeTypeDisplay
            tvJdryxm.text = jdryxm
        }
    }

}