package com.unicorn.forensic2.refactor.home

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.setIIcon
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.SetCurrentItemEvent
import com.unicorn.forensic2.data.model.HomeMenu
import com.unicorn.forensic2.ui.act.*
import com.unicorn.forensic2.ui.act.list.PsxxListAct
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.operation.tztx.TztxAct
import kotlinx.android.synthetic.main.item_home_menu.*
import kotlinx.android.synthetic.main.item_home_menu.ivImg
import kotlinx.android.synthetic.main.item_home_menu.root
import kotlinx.android.synthetic.main.item_home_menu.tvName
import kotlinx.android.synthetic.main.item_home_operation.*

class HomeOperationAdapter : BaseQuickAdapter<HomeOperation, KVHolder>(R.layout.item_home_operation) {

    override fun convert(helper: KVHolder, item: HomeOperation) {
        helper.apply {
            tvName.text = item.namez
            ivImg.setIIcon(mContext,item.icon,R.color.colorPrimary)
        }
        helper.apply {
            root.safeClicks().subscribe {
                when (item) {
                    HomeOperation.JGCX -> mContext.startAct(JdjgPublicListAct::class.java)
                    HomeOperation.XTGG -> mContext.startAct(XtggListAct::class.java)
                    HomeOperation.TSJY -> mContext.startAct(TsjyAct::class.java)
                    HomeOperation.TZTX -> mContext.startAct(TztxAct::class.java)
                    HomeOperation.JGXX -> mContext.startAct(JdjgMyGuideAct::class.java)
                    HomeOperation.PSXX -> mContext.startAct(PsxxListAct::class.java)
                    HomeOperation.ZJXX -> mContext.startAct(ExpertGuideAct::class.java)
                    HomeOperation.WDAJ, HomeOperation.WDPS -> RxBus.post(SetCurrentItemEvent())
                    else -> {
                    }
                }
            }
        }
    }

}