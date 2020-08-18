package com.unicorn.forensic2.refactor.stat

import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_stat.*

class StatAdapter : BaseQuickAdapter<Stat, KVHolder>(R.layout.item_stat) {

    override fun convert(helper: KVHolder, item: Stat) {
        helper.apply {
            val index = data.indexOf(item)
            root.background = ColorDrawable(
                ContextCompat.getColor(
                    mContext,
                    if (index % 2 == 0) R.color.md_white else R.color.md_grey_100
                )
            )
        }
        helper.apply {
            tvDqmc.text =item.dqmc
            tvJc.text = item.jz.toString()
            tvXs.text = item.sz.toString()
            tvJa.text = item.ja.toString()
            tvWj.text = item.jy.toString()
            tvJal.text = "${item.jal}%"
        }
    }

}