package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.KVHolder

class JdjgMyAdapter : BaseQuickAdapter<Jdjg, KVHolder>(R.layout.item_jdjg_my) {

    override fun convert(helper: KVHolder, item: Jdjg) {
//        helper.apply {
//            tvJgmc.text = item.jgmc
//        }
//        helper.apply {
//            root.safeClicks().subscribe {
//                Intent(mContext, JdjgDetailAct::class.java).apply {
//                    putExtra(JdjgId, item.jgid)
//                }.let { mContext.startActivity(it) }
//            }
//        }
    }

}