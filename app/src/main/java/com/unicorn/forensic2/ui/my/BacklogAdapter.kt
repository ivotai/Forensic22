package com.unicorn.forensic2.ui.my

import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.event.SetCurrentItemEvent
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_backlog.*

class BacklogAdapter : BaseQuickAdapter<Backlog, KVHolder>(R.layout.item_backlog) {

    override fun convert(helper: KVHolder, item: Backlog) {
        helper.apply {
            tvCount.text = item.count.toString()
            tvCaseType.text = item.caseType.cn
        }
        helper.apply {
            root.safeClicks().subscribe {
                when(item.caseType){
                    CaseType.TZTX -> ToastUtils.showShort("尚未实现")
                    CaseType.DPS -> RxBus.post(SetCurrentItemEvent())
                    else -> {
                        RxBus.post(item.caseType)
                        RxBus.post(SetCurrentItemEvent())
                    }
                }
            }
        }
    }

}