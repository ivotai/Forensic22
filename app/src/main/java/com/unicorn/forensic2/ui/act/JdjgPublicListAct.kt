package com.unicorn.forensic2.ui.act

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.defaultPadding
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.act.query.JdjgPublicQueryAct
import com.unicorn.forensic2.ui.adapter.JdjgPublicAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class JdjgPublicListAct : SimplePageAct<Jdjg, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构列表")
        mRecyclerView.addItemDecoration(LinearSpanDecoration(defaultPadding))
    }

    override fun bindIntent() {
        super.bindIntent()
        titleBar.setOperation("搜索").safeClicks()
            .subscribe { startAct(JdjgPublicQueryAct::class.java) }
        startAct(JdjgPublicQueryAct::class.java)
    }

    override val simpleAdapter: BaseQuickAdapter<Jdjg, KVHolder> = JdjgPublicAdapter()

    private var queryMap = HashMap<String, Any>()

    override fun loadPage(page: Int): Single<Page<Jdjg>> =
        v1Api.getJdjgList(page = page, queryMap = queryMap)

    override fun registerEvent() {
        RxBus.registerEvent(this, QueryMapEvent::class.java, Consumer {
            queryMap = it.queryMap
            loadFirstPage()
        })
    }

}
