package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Zjzz
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_zjzz.*

class ZjzzAdapter : BaseQuickAdapter<Zjzz, KVHolder>(R.layout.item_zjzz) {

    override fun convert(helper: KVHolder, item: Zjzz) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvZczyh.text = item.zczyh
            tvGrzc.text = item.grzc
            tvZyzsyxq.text = item.zyzsyxq.toDisplayFormat()
        }
    }

}