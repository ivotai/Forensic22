package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Psxx
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_psxx.*

class PsxxAdapter : BaseQuickAdapter<Psxx, KVHolder>(R.layout.item_psxx) {

    override fun convert(helper: KVHolder, item: Psxx) {
        helper.apply {
            tvFileName.text = item.fileName
            tvBeginDate.text = item.beginDate.toDisplayFormat()
            tvEndDate.text = item.endDate.toDisplayFormat()
            tvStatus.text = item.status.toString()
        }
    }

}