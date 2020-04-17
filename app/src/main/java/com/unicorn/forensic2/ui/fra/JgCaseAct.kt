package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.JgCaseType
import com.unicorn.forensic2.data.model.JgCaseTypeS
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.JgCaseTypeAdapter
import com.unicorn.forensic2.ui.adapter.JgCaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_jg_case.*

class JgCaseAct : SimplePageAct<Case, KVHolder>() {

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (caseType) {
            JgCaseType.ZBTZ -> v1Api.getZbtzList(page = page)
            JgCaseType.DJD -> v1Api.getDjdList(page = page)
            JgCaseType.YJD -> v1Api.getYjdList(page = page)
            JgCaseType.YJJ -> v1Api.getYjjList(page = page)
            JgCaseType.YXA -> v1Api.getYxaList(page = page)
        }
    }

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构案件", true)
        rvCaseType.apply {
            layoutManager = LinearLayoutManager(context)
            jgCaseTypeAdapter.bindToRecyclerView(this)
        }
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        val data = JgCaseType.all.map { JgCaseTypeS(it) }
        data.forEach { it.isSelect = it.jgCaseType == firstCaseType }
        jgCaseTypeAdapter.setNewData(data)
        RxBus.post(firstCaseType)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JgCaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
    }

    private val jgCaseTypeAdapter = JgCaseTypeAdapter()

    override val simpleAdapter = JgCaseAdapter()

    private var caseType = JgCaseType.all[0]

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.act_jg_case

    private val firstCaseType by lazy { intent.getSerializableExtra(Param) as JgCaseType }

}