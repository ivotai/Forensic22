package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdjg.*

class JdjgAdapter : BaseQuickAdapter<Jdjg, KVHolder>(R.layout.item_jdjg) {

    override fun convert(helper: KVHolder, item: Jdjg) {
        helper.apply {
            tvJgmc.text = item.jgmc
        }
    }

}