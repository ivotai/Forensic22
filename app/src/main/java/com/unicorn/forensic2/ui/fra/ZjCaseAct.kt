package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.*
import com.unicorn.forensic2.ui.adapter.JgCaseTypeAdapter
import com.unicorn.forensic2.ui.adapter.JgCaseAdapter
import com.unicorn.forensic2.ui.adapter.ZjCaseAdapter
import com.unicorn.forensic2.ui.adapter.ZjCaseTypeAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_jg_case.*

class ZjCaseAct : SimplePageAct<ZjCase, KVHolder>() {

    override fun loadPage(page: Int): Single<Page<ZjCase>> {
        return when (caseType) {
            ZjCaseType.DPS -> v1Api.getDpsList(page = page)
            ZjCaseType.YPS -> v1Api.getYwcList(page = page)
        }
    }

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("专家案件", true)
        rvCaseType.apply {
            layoutManager = LinearLayoutManager(context)
            zjCaseTypeAdapter.bindToRecyclerView(this)
        }
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        val data = ZjCaseType.all.map { ZjCaseTypeS(it) }
        data[0].isSelect = true
        zjCaseTypeAdapter.setNewData(data)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, ZjCaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
    }

    private val zjCaseTypeAdapter = ZjCaseTypeAdapter()

    override val simpleAdapter = ZjCaseAdapter()

    private var caseType = ZjCaseType.all[0]

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.act_jg_case

}