package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.data.model.Jgzz
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jgzz_my.*

class JgzzMyAdapter : BaseQuickAdapter<Jgzz, KVHolder>(R.layout.item_jgzz_my) {

    override fun convert(helper: KVHolder, item: Jgzz) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvCyly.text = item.cyly
            tvSpjg.text= item.spjg
            tvZzsm.text = item.zzsm

            tvZzdj.text = item.zzdj
            tvYxrq.text = item.yxrq.toDisplayFormat2()
            tvZzzh.text = item.zzzh
        }
    }

}