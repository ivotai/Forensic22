package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDateFormat
import com.unicorn.forensic2.data.model.Tsjy
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_tsjy_yhf.*

class TsjyYhfAdapter : BaseQuickAdapter<Tsjy, KVHolder>(R.layout.item_tsjy_yhf) {

    override fun convert(helper: KVHolder, item: Tsjy) {
        helper.apply {
            tvComplainant.text = item.complainant
            tvIdCard.text = item.idCard
            tvCreatedDate.text  = item.createdDate.toDateFormat()
            tvContent.text = item.content
        }
//        helper.apply {
//            root.safeClicks().subscribe {
//                Intent(mContext, JdjgDetailAct::class.java).apply {
//                    putExtra(JdjgId, item.jgid)
//                }.let { mContext.startActivity(it) }
//            }
//        }
    }

}