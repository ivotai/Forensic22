package com.unicorn.forensic2.ui.other

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.DsrCase
import com.unicorn.forensic2.data.model.Case
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_case_detail.view.*

class DsrCaseDetailHeader(context: Context, dsrCase: DsrCase) : FrameLayout(context),
    LayoutContainer {

    override val containerView = this

    init {
        LayoutInflater.from(context).inflate(R.layout.header_case_detail, this, true)
        with(dsrCase) {
            tvJdNo.text = jdNo
            tvCaseNo.text = caseNo
            tvCaseStatus.text = closeTypeDisplay
            tvDateApply.text = dateApply.toDisplayFormat()
            tvPlanFinish.text = ""
            tvCloseTypeDisplay.text = closeTypeDisplay
            tvJdryxm.text = plaintiff
        }
    }

}