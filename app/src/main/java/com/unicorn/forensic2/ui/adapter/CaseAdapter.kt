package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.displayDateFormat2
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case.*
import org.joda.time.DateTime

class CaseAdapter : BaseQuickAdapter<Case, KVHolder>(R.layout.item_case) {

    override fun convert(helper: KVHolder, item: Case) {
        helper.apply {
            tvJdNo.text = "鉴定案号：${item.jdNo}"
            tvLarq.text = "立案日期：${DateTime(item.larq).toString(displayDateFormat2)}"
            tvCaseNo.text = "案件案号：${item.caseNo}"
        }
        helper.root.safeClicks().subscribe {
//            mContext.startActivity(Intent(mContext, CazeDetailAct::class.java).apply {
//                putExtra(CaseId, item.caseId)
//            })
        }
    }

}