package com.unicorn.forensic2.ui.jdxx

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdxx.*

class JdxxAdapter : BaseQuickAdapter<Jdxx, KVHolder>(R.layout.item_jdxx) {

    override fun convert(helper: KVHolder, item: Jdxx) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvDateLottery.text = item.dateLottery.toDisplayFormat()
            tvJgmc.text = item.jgmc
            tvZbtz.text = item.fidzbtz?.filename ?: ""
            tvYhhf.text = item.fidyhhf
            tvJdbg.text = ""// todo fidjdbg
        }
    }

}