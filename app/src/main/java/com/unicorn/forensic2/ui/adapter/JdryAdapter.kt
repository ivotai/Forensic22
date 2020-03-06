package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case.*

class JdryAdapter : BaseQuickAdapter<Case, KVHolder>(R.layout.item_jdry) {

    override fun convert(helper: KVHolder, item: Case) {
        helper.apply {
        }
        helper.root.safeClicks().subscribe {
        }
    }

}