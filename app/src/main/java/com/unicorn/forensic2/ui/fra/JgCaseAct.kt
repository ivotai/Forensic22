package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.JdjgAdminCaseType
import com.unicorn.forensic2.data.model.JdjgAdminCaseTypeS
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.JdjgAdminCaseTypeSAdapter
import com.unicorn.forensic2.ui.adapter.JdjgAdminCaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_case_list.*

class JgCaseAct : SimplePageAct<Case, KVHolder>() {

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (caseType) {
            JdjgAdminCaseType.ZBTZ -> v1Api.getZbtzList(page = page)
            JdjgAdminCaseType.DJD -> v1Api.getDjdList(page = page)
            JdjgAdminCaseType.YJD -> v1Api.getYjdList(page = page)
            JdjgAdminCaseType.YJJ -> v1Api.getYjjList(page = page)
            JdjgAdminCaseType.YXA -> v1Api.getYxaList(page = page)
            JdjgAdminCaseType.CLOSE -> v1Api.getClose(page = page)
            JdjgAdminCaseType.UNCLOSE -> v1Api.getUnClose(page = page)
        }
    }

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构案件", true)
        rvCaseTypeS.apply {
            layoutManager = LinearLayoutManager(context)
            jgCaseTypeAdapter.bindToRecyclerView(this)
        }
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        val data = JdjgAdminCaseType.all.map { JdjgAdminCaseTypeS(it) }
        data.forEach { it.isSelect = it.jdjgAdminCaseType == firstCaseType }
        jgCaseTypeAdapter.setNewData(data)
        RxBus.post(firstCaseType)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdjgAdminCaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
    }

    private val jgCaseTypeAdapter = JdjgAdminCaseTypeSAdapter()

    override val simpleAdapter = JdjgAdminCaseAdapter()

    private var caseType = JdjgAdminCaseType.all[0]

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.fra_case_list

    private val firstCaseType by lazy { intent.getSerializableExtra(Param) as JdjgAdminCaseType }

}