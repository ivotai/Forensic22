package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.data.model.Xtgg
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_xtgg.*
import org.joda.time.DateTime
import java.util.*

class XtggAdapter : BaseQuickAdapter<Xtgg, KVHolder>(R.layout.item_xtgg) {

    override fun convert(helper: KVHolder, item: Xtgg) {
        helper.apply {
            tvTitle.text = item.title
            tvDatePublished.text = DateTime(Date(item.datepublished)).toString(displayDateFormat)
        }
    }

}