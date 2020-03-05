package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Xtgg
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Xtgg
import com.unicorn.forensic2.ui.act.XtggDetailAct
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
        helper.apply {
            root.safeClicks().subscribe {
                Intent(mContext, XtggDetailAct::class.java).apply {
                    putExtra(Xtgg, item)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}