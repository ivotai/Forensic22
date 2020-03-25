package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Roll
import com.unicorn.forensic2.ui.act.RollDetailAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_roll.*

class RollAdapter : BaseQuickAdapter<Roll, KVHolder>(R.layout.item_roll) {

    override fun convert(helper: KVHolder, item: Roll) {
        helper.apply {
            tvJdNo.text = item.jdNo
            tvCaseNo.text = "审判案号：  ${item.caseNo}"
            tvCaseStatus.text = "鉴定状态：  ${item.caseStatus}"
            tvJdlb.text = "鉴定类别：  ${item.jdlb}"
        }
        helper.apply {
            root.safeClicks().subscribe {
                Intent(mContext, RollDetailAct::class.java).apply {
                    putExtra(Param, item)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}