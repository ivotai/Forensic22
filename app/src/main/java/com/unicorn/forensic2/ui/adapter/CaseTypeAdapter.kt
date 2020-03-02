package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_type.*

class CaseTypeAdapter : BaseQuickAdapter<CaseType, KVHolder>(R.layout.item_case_type) {

    override fun convert(helper: KVHolder, item: CaseType) {
        helper.apply {
            tvName.text = item.namez
        }
    }

}