package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.act.CaseDetailAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case.*

class JdjgAdminCaseAdapter : BaseQuickAdapter<Case, KVHolder>(R.layout.item_case) {

    override fun convert(helper: KVHolder, item: Case) {
        helper.apply {
            tvJdNo.text = item.jdNo
            tvCourtNameApply.text = item.courtNameApply
            tvCaseType.text = item.caseType
            tvCaseStatus.text = item.caseStatus
            tvCaseNo.text = item.caseNo
            tvCourtNameAccept.text = item.courtNameAccept
        }
        helper.root.safeClicks().subscribe {
            Intent(mContext, CaseDetailAct::class.java).apply {
                putExtra(Param, item)
            }.let { mContext.startActivity(it) }
        }
    }

}