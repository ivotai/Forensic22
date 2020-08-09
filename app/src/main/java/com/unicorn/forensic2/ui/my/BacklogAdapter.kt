package com.unicorn.forensic2.ui.my

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.SetCurrentItemEvent
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.operation.tztx.TztxAct
import kotlinx.android.synthetic.main.item_backlog.*

class BacklogAdapter : BaseQuickAdapter<Backlog, KVHolder>(R.layout.item_backlog) {

    override fun convert(helper: KVHolder, item: Backlog) {
        helper.apply {
            tvCount.text = item.count.toString()
            tvCaseType.text = item.cnX
        }
        helper.apply {
            root.safeClicks().subscribe {
                when (item) {
                    Backlog.tztx -> context.startAct(TztxAct::class.java)
                    Backlog.dps -> RxBus.post(SetCurrentItemEvent())
                    else -> {
                        RxBus.post(item.caseType!!)
                        RxBus.post(SetCurrentItemEvent())
                    }
                }
            }
        }
    }

}