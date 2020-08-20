package com.unicorn.forensic2.refactor.main.home

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.event.SetCurrentItemEvent
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.stat.StatAct
import com.unicorn.forensic2.refactor.tztx.TztxAct
import com.unicorn.forensic2.ui.act.*
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_home_menu.*

class HomeOperationAdapter :
    BaseQuickAdapter<HomeOperation, KVHolder>(R.layout.item_home_operation) {

    override fun convert(helper: KVHolder, item: HomeOperation) {
        helper.apply {
            tvName.text = item.namez
            Glide.with(context).load(item.iconRes).into(ivImg)
        }
        helper.apply {
            root.safeClicks().subscribe {
                when (item) {
                    HomeOperation.JGCX -> mContext.startAct(JdjgPublicListAct::class.java)
                    HomeOperation.XTGG -> mContext.startAct(XtggListAct::class.java)
                    HomeOperation.TSJY -> mContext.startAct(TsjyAct::class.java)
                    HomeOperation.TZTX -> mContext.startAct(TztxAct::class.java)
                    HomeOperation.JGXX -> mContext.startAct(JdjgMyGuideAct::class.java)
                    HomeOperation.ZJXX -> mContext.startAct(ExpertGuideAct::class.java)
                    HomeOperation.WDPS -> RxBus.post(SetCurrentItemEvent())
                    HomeOperation.SPSX -> {
                        if (role == Role.Sfjd) {
                            role = Role.SfjdAdmin
                            RxBus.post(LoginStateChangeEvent())
                        }
                        RxBus.post(SetCurrentItemEvent())
                    }
                    HomeOperation.WDAJ -> {
                        if (role == Role.SfjdAdmin) {
                            role = Role.Sfjd
                            RxBus.post(LoginStateChangeEvent())
                        }
                        RxBus.post(SetCurrentItemEvent())
                    }
                    HomeOperation.SJCTX -> mContext.startAct(StatAct::class.java)
                    else -> {
                    }
                }
            }
        }
    }

}