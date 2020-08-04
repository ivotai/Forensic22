package com.unicorn.forensic2.ui.operation.jdfk

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdry_my.*

class JdrySAdapter : BaseQuickAdapter<JdryS, KVHolder>(R.layout.item_jdry_s) {

    override fun convert(helper: KVHolder, item: JdryS) {
        helper.apply {
            tvXm.text = item.jdry.xm
            tvXm.setTextColor(
                ContextCompat.getColor(
                    context,
                    if (item.isSelected) R.color.md_white else R.color.md_black
                )
            )
            tvXm.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    if (item.isSelected) R.color.colorPrimary else R.color.md_white
                )
            )
        }

        helper.apply {
            tvXm.safeClicks().subscribe {
                item.isSelected = !item.isSelected
                notifyDataSetChanged()
            }
        }
    }

    val jdryListSelected: List<JdrySimple> get() = data.filter { it.isSelected }.map { it.jdry }

}