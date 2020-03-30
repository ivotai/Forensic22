package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Shjl
import com.unicorn.forensic2.ui.act.query.ShjlJdjgQueryAct
import com.unicorn.forensic2.ui.adapter.ShjlAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class ShjlJdjgListAct : SimplePageAct<Shjl, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("审核记录")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        titleBar.setOperation("搜索").safeClicks()
            .subscribe { startAct(ShjlJdjgQueryAct::class.java) }
    }

    override val simpleAdapter: BaseQuickAdapter<Shjl, KVHolder> = ShjlAdapter()

    override fun loadPage(page: Int): Single<Page<Shjl>> =
        v1Api.getShjlJdjgList(page = page, queryMap = queryMap)

    private var queryMap = HashMap<String, Any>()

    override fun registerEvent() {
        RxBus.registerEvent(this, QueryMapEvent::class.java, Consumer {
            queryMap = it.queryMap
            queryMap["Jgid"] = jgId
            loadFirstPage()
        })
    }

    private val jgId by lazy { intent.getStringExtra(Param) }

}
