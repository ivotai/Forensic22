package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.TsjyId
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.data.model.Tsjy
import com.unicorn.forensic2.ui.act.AddTsjyReplyAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_tsjy_whf.*

class TsjyWhfAdapter : BaseQuickAdapter<Tsjy, KVHolder>(R.layout.item_tsjy_whf) {

    override fun convert(helper: KVHolder, item: Tsjy) {
        helper.apply {
            tvComplainant.text = item.complainant
            tvIdCard.text = item.idCard
            tvCreatedDate.text  = item.createdDate.toDisplayFormat2()
            tvContent.text = item.content
        }
//        helper.apply {
//            tvReply.safeClicks().subscribe {
//                Intent(mContext, AddTsjyReplyAct::class.java).apply {
//                    putExtra(TsjyId, item.objectId)
//                }.let { mContext.startActivity(it) }
//            }
//        }
    }

}