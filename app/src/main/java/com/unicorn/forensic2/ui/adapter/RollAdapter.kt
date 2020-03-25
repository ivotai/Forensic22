package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Psxx
import com.unicorn.forensic2.data.model.Roll
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_psxx.*

class RollAdapter : BaseQuickAdapter<Roll, KVHolder>(R.layout.item_roll) {

    override fun convert(helper: KVHolder, item: Roll) {
        helper.apply {

        }
    }

}