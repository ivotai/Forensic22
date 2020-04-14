package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.data.model.JgCaseType
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_type.*

class JgCaseTypeAdapter : BaseQuickAdapter<JgCaseType, KVHolder>(R.layout.item_case_type) {

    override fun convert(helper: KVHolder, item: JgCaseType) {
        helper.apply {
            tvText.text = item.text
        }
        helper.apply {
            tvText.clicks().subscribe { RxBus.post(item) }
        }
    }

}