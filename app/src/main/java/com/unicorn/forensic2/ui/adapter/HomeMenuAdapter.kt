package com.unicorn.forensic2.ui.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.model.HomeMenu
import com.unicorn.forensic2.ui.act.JdjgListAct
import com.unicorn.forensic2.ui.act.XtggListAct
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_home_menu.*

class HomeMenuAdapter : BaseQuickAdapter<HomeMenu, KVHolder>(R.layout.item_home_menu) {

    override fun convert(helper: KVHolder, item: HomeMenu) {
        helper.apply {
            tvName.text = item.namez
            Glide.with(mContext).load(item.imgRes).into(ivImg)
        }
        helper.apply {
            root.safeClicks().subscribe {
                when (item) {
                    HomeMenu.JGCX -> mContext.startAct(JdjgListAct::class.java)
                    HomeMenu.XTGG -> mContext.startAct(XtggListAct::class.java)
                    else -> {
                    }
                }
            }
        }
    }

}