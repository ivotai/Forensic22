package com.unicorn.forensic2.refactor.main.profile.backlog

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.SetCurrentItemEvent
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.refactor.tztx.TztxAct
import kotlinx.android.synthetic.main.item_backlog.*

class BacklogAdapter : BaseQuickAdapter<Backlog, KVHolder>(R.layout.item_backlog) {

    override fun convert(helper: KVHolder, item: Backlog) {
        helper.apply {
            tvCount.text = item.value
            tvCaseType.text = item.name
        }
        helper.apply {
            root.safeClicks().subscribe {
                when (val backlogKey = BacklogKey.findByKey(item.key)) {
                    BacklogKey.tztx -> context.startAct(TztxAct::class.java)
                    BacklogKey.dps -> RxBus.post(SetCurrentItemEvent())
                    else -> {
                        RxBus.post(backlogKey.caseType!!)
                        RxBus.post(SetCurrentItemEvent())
                    }
                }
            }
        }
    }

}