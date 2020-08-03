package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.CaseTypeS
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseTypeSAdapter
import com.unicorn.forensic2.ui.adapter.CaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_case_list.*

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    override fun initViews() {
        super.initViews()

        titleBar.setTitle("我的案件", false)

        rvCaseTypeS.apply {
            layoutManager = LinearLayoutManager(context)
            jdjgAdminCaseTypeSAdapter.bindToRecyclerView(this)
        }
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        val data = CaseType.all.map { CaseTypeS(it) }
        data.forEach { it.isSelect = it.caseType == jdjgAdminCaseType }
        jdjgAdminCaseTypeSAdapter.setNewData(data)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, CaseType::class.java, Consumer {
            jdjgAdminCaseType = it
            loadFirstPage()
        })
    }

    override val simpleAdapter: BaseQuickAdapter<Case, KVHolder> = CaseAdapter()

    private val jdjgAdminCaseTypeSAdapter = CaseTypeSAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (jdjgAdminCaseType) {
            CaseType.ZBTZ -> api.getZbtzList(page = page)
            CaseType.DJD -> api.getDjdList(page = page)
            CaseType.YJD -> api.getYjdList(page = page)
            CaseType.YJJ -> api.getYjjList(page = page)
            CaseType.YXA -> api.getYxaList(page = page)
            CaseType.CLOSE -> api.getClose(page = page)
            CaseType.UNCLOSE -> api.getUnClose(page = page)
        }
    }

    private var jdjgAdminCaseType = CaseType.default

    //

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.fra_case_list
}