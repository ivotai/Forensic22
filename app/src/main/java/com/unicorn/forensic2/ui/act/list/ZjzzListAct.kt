package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Zjzz
import com.unicorn.forensic2.ui.act.addOrEdit.ZjzzAddAct
import com.unicorn.forensic2.ui.adapter.ZjzzAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class ZjzzListAct : SimplePageAct<Zjzz, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("专家资质")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        titleBar.setOperation("添加").safeClicks().subscribe { startAct(ZjzzAddAct::class.java) }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            loadFirstPage()
        })
    }

    override val simpleAdapter: BaseQuickAdapter<Zjzz, KVHolder> = ZjzzAdapter()

    override fun loadPage(page: Int): Single<Page<Zjzz>> = v1Api.getZjzzList(page = page)

}
