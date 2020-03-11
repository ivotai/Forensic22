package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.JdjgId
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.act.JdjgPublicDetailAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdjg.*

class JdjgPublicAdapter : BaseQuickAdapter<Jdjg, KVHolder>(R.layout.item_jdjg) {

    override fun convert(helper: KVHolder, item: Jdjg) {
        helper.apply {
            tvJgmc.text = item.jgmc
        }
        helper.apply {
            root.safeClicks().subscribe {
                Intent(mContext, JdjgPublicDetailAct::class.java).apply {
                    putExtra(JdjgId, item.jgid)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}