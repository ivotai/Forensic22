package com.unicorn.forensic2.ui.adapter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.data.model.JdjgAdminCaseTypeS
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_type.*

class JdjgAdminCaseTypeSAdapter : BaseQuickAdapter<JdjgAdminCaseTypeS, KVHolder>(R.layout.item_case_type) {

    override fun convert(helper: KVHolder, item: JdjgAdminCaseTypeS) {
        helper.apply {
            tvText.text = item.jdjgAdminCaseType.text
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
                RxBus.post(item.jdjgAdminCaseType)
            }
        }
    }

}