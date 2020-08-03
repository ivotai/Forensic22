package com.unicorn.forensic2.ui.jdxx

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case.*

class JdxxAdapter : BaseQuickAdapter<Jdxx, KVHolder>(R.layout.item_jdxx) {

    override fun convert(helper: KVHolder, item: Jdxx) {
        helper.apply {
            tvJdNo.text = item.jdNo
            tvCourtNameApply.text = item.courtNameApply
            tvCaseType.text = item.caseType
            tvCaseStatus.text = item.caseStatus
            tvCaseNo.text = item.caseNo
            tvCourtNameAccept.text = item.courtNameAccept
        }
    }

}