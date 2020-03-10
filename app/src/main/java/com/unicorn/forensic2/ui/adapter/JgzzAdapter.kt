package com.unicorn.forensic2.ui.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.app.toDateTime
import com.unicorn.forensic2.data.model.Jgzz
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jgzz.*

class JgzzAdapter : BaseQuickAdapter<Jgzz, KVHolder>(R.layout.item_jgzz) {

    override fun convert(helper: KVHolder, item: Jgzz) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvCyly.text = item.cyly
            tvSpjg.text= item.spjg
            tvZzsm.text = item.zzsm

            tvZzdj.text = item.zzdj
            tvYxrq.text = item.yxrq.toDisplayFormat()
            tvZzzh.text = item.zzzh

            // 是否失效
            ivSx.visibility =
                if (item.yxrq.toDateTime().isAfterNow) View.INVISIBLE else View.VISIBLE

        }
    }

}