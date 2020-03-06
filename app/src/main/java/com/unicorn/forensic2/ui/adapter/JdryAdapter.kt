package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDateFormat
import com.unicorn.forensic2.data.model.Jdry
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdry.*

class JdryAdapter : BaseQuickAdapter<Jdry, KVHolder>(R.layout.item_jdry) {

    override fun convert(helper: KVHolder, item: Jdry) {
        helper.apply {
            tvXm.text = item.xm
            tvGrzc.text = item.grzc
            tvZczyh.text = item.zczyh
            tvJdlb.text = item.jdlb
            tvZyzsyxq.text = item.zyzsyxq.toDateFormat()
        }
    }

}