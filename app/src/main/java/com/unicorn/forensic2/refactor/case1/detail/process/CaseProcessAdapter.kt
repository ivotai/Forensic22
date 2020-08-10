package com.unicorn.forensic2.refactor.case1.detail.process

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_process.*

class CaseProcessAdapter : BaseQuickAdapter<CaseProcess, KVHolder>(R.layout.item_case_process) {

    override fun convert(helper: KVHolder, item: CaseProcess) {
        helper.apply {
            tvStatusDb.text = item.status
            tvAcceptDttm.text = item.acceptDttm.toDisplayFormat()
            tvHandler.text = item.handler
            tvHandleDttm.text = item.handleDttm.toDisplayFormat()
            tvRemark.text = item.remark
        }
    }

}