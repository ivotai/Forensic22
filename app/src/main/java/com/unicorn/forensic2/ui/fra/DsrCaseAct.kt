package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.DsrCase
import com.unicorn.forensic2.data.model.*
import com.unicorn.forensic2.ui.adapter.DsrCaseAdapter
import com.unicorn.forensic2.ui.adapter.DsrCaseTypeAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_case_list.*

class DsrCaseAct : SimplePageAct<DsrCase, KVHolder>() {

    override fun loadPage(page: Int): Single<Page<DsrCase>> {
        return v1Api.getMyCaseList(page = page)
    }

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("当事人案件", true)
        rvCaseTypeS.apply {
            layoutManager = LinearLayoutManager(context)
            dsrCaseTypeAdapter.bindToRecyclerView(this)
        }
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        val data = DsrCaseType.all.map { DsrCaseTypeS(it) }
        data.forEach { it.isSelect = it.dsrCaseType == firstCaseType }
        dsrCaseTypeAdapter.setNewData(data)
        RxBus.post(firstCaseType)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, DsrCaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
    }

    private val dsrCaseTypeAdapter = DsrCaseTypeAdapter()

    override val simpleAdapter = DsrCaseAdapter()

    private var caseType = DsrCaseType.MYCASE

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.fra_case_list

    private val firstCaseType = DsrCaseType.MYCASE

}