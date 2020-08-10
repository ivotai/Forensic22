package com.unicorn.forensic2.refactor.case1.detail.process

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.vipulasri.timelineview.TimelineView
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDateTime
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_process.*

class CaseProcessAdapter : BaseQuickAdapter<CaseProcess, KVHolder>(R.layout.item_case_process) {

    override fun convert(helper: KVHolder, item: CaseProcess) {
        helper.apply {
            tvHandleDttm.text = item.handleDttm.toDateTime().toString("yy/MM/dd")
            tvStatus.text = "${item.handler}【${item.status}】"
            tvRemark.text = item.remark ?: "无备注事项"
        }
    }

    // for time line

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KVHolder {
        val viewHolder = super.onCreateViewHolder(parent, viewType)
        viewHolder.timelineView.initLine(viewType)
        viewHolder.timelineView.setMarkerColor(
            ContextCompat.getColor(
                mContext,
                R.color.colorPrimary
            )
        )
        return viewHolder
    }

}