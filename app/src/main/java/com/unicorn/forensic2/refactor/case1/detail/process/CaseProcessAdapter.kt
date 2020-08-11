package com.unicorn.forensic2.refactor.case1.detail.process

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.vipulasri.timelineview.TimelineView
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDateTime
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_process.*

class CaseProcessAdapter : BaseQuickAdapter<CaseProcess, KVHolder>(R.layout.item_case_process) {

    var totalElements = 0

    override fun convert(helper: KVHolder, item: CaseProcess) {
        helper.apply {
            tvHandleDttm.text = item.handleDttm.toDateTime().toString("yy/MM/dd")
            tvStatus.text = "${item.handler}【${item.status}】"
            tvRemark.text = item.remark ?: "无备注事项"
        }
        helper.apply {
            val viewType = TimelineView.getTimeLineViewType(data.indexOf(item), totalElements)
            timelineView.initLine(viewType)
            timelineView.setMarkerColor(
                ContextCompat.getColor(
                    mContext,
                    R.color.colorPrimary
                )
            )
        }
    }

}