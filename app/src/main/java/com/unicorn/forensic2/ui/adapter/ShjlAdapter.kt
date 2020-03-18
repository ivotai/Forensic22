package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Shjl
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_shjl.*

class ShjlAdapter : BaseQuickAdapter<Shjl, KVHolder>(R.layout.item_shjl) {

    override fun convert(helper: KVHolder, item: Shjl) {
        helper.apply {
            tvJgmc.text = item.jgmc
            tvDate.text = item.createdDate.toDisplayFormat()
            tvRemark.text = item.remark
            tvResult.text = when (item.dataflag) {
                1 -> "待审核"
                2 -> "审核通过"
                3 -> "审核不通过"
                4 -> "待公示"
                5 -> "公示中"
                6 -> "公示通过"
                7 -> "公示不通过"
                else -> "赞同"
            }
        }
    }

}