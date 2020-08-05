package com.unicorn.forensic2.ui.operation.tztx

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_tztx.*

class TztxAdapter : BaseQuickAdapter<Tztx, KVHolder>(R.layout.item_tztx) {

    override fun convert(helper: KVHolder, item: Tztx) {
        helper.apply {
            tvSendtime.text = "${item.sendtime.toDisplayFormat2()} 发来"
            tvMsg.text = item.notification.msg
            tvReadtime.text = "${item.readtime.toDisplayFormat2()} 阅读"
        }
    }

}