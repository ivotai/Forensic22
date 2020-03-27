package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.defaultPadding
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Roll
import com.unicorn.forensic2.ui.act.query.RollQueryAct
import com.unicorn.forensic2.ui.adapter.RollAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class RollListAct : SimplePageAct<Roll, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("鉴定摇号")
        mRecyclerView.addItemDecoration(LinearSpanDecoration(defaultPadding))
    }


    override fun bindIntent() {
        super.bindIntent()
        titleBar.setOperation("搜索").safeClicks()
            .subscribe { startAct(RollQueryAct::class.java) }
    }

    override val simpleAdapter: BaseQuickAdapter<Roll, KVHolder> = RollAdapter()

    override fun loadPage(page: Int): Single<Page<Roll>> =
        v1Api.getRollList(page = page, queryMap = queryMap)

    private var queryMap = HashMap<String, Any>()

    override fun registerEvent() {
        RxBus.registerEvent(this, QueryMapEvent::class.java, Consumer {
            queryMap = it.queryMap
            loadFirstPage()
        })
    }

}
