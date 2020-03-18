package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.ShjlJdjg
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_shjl_jdjg.*

class ShjlJdjgAdapter : BaseQuickAdapter<ShjlJdjg, KVHolder>(R.layout.item_shjl_jdjg) {

    override fun convert(helper: KVHolder, item: ShjlJdjg) {
        helper.apply {
            tvJgmc.text = item.jgmc
            tvDate.text = item.lastUpdateDate.toDisplayFormat()
            tvRemark.text = item.remark
            tvResult.text = if (item.dataflag == 1) "通过" else "不通过"
        }
    }

}