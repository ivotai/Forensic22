package com.unicorn.forensic2.refactor.case1

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Case
import com.unicorn.forensic2.app.CaseType
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.refactor.case1.detail.CaseDetailAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case.*

class CaseAdapter : BaseQuickAdapter<Case, KVHolder>(R.layout.item_case) {

    lateinit var caseType: CaseType

    override fun convert(helper: KVHolder, item: Case) {
        helper.apply {
            tvJdNo.text = item.jdNo
            tvCourtNameApply.text = item.courtNameApply
            tvCaseType.text = item.caseTypeX
            tvCaseStatus.text = item.caseStatusX
            tvCaseNo.text = item.caseNo
            tvCourtNameAccept.text = item.courtNameAccept
        }
        helper.root.safeClicks().subscribe {
            if (caseType in listOf(com.unicorn.forensic2.data.model.CaseType.YJJ,
                    com.unicorn.forensic2.data.model.CaseType.YQX
                )) return@subscribe

            Intent(mContext, CaseDetailAct::class.java).apply {
                putExtra(Case, item)
                putExtra(CaseType, caseType)
            }.let { mContext.startActivity(it) }
        }
    }

}