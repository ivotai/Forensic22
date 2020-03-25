package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Roll
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_roll_detail.*

class RollDetailAct : BaseAct() {

    override val layoutId = R.layout.act_roll_detail

    override fun initViews() {
        titleBar.setTitle("摇号详情")
        fun display() = with(roll) {
            tvJdNo.text = jdNo
            tvCaseNo.text = caseNo
            tvPlaintiff.text = plaintiff
            tvCourtNameApply.text = courtNameApply
            tvCourtLxdhAccept.text = courtLxdhAccept
            tvJdlb.text = jdlb
            tvCaseStatus.text = caseStatus
            tvZbr.text = zbr
            tvDateAccept.text = dateAccept.toDisplayFormat()
        }
        display()
    }

    private val roll by lazy { intent.getSerializableExtra(Param) as Roll }

}