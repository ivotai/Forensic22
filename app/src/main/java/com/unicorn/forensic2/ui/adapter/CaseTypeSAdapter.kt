package com.unicorn.forensic2.ui.adapter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.data.model.CaseTypeS
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_type.*

class CaseTypeSAdapter : BaseQuickAdapter<CaseTypeS, KVHolder>(R.layout.item_case_type) {

    override fun convert(helper: KVHolder, item: CaseTypeS) {
        helper.apply {
            tvText.text = item.caseType.text
            val textColor = ContextCompat.getColor(
                mContext,
                if (item.isSelect) R.color.md_red_400 else R.color.md_black
            )
            tvText.setTextColor(textColor)
        }
        helper.apply {
            tvText.clicks().subscribe {
                data.forEach { it.isSelect = it == item }
                notifyDataSetChanged()
                RxBus.post(item.caseType)
            }
        }
    }

}