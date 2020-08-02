package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.JdjgAdminCaseType
import com.unicorn.forensic2.data.model.JdjgAdminCaseTypeS
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.JdjgAdminCaseTypeSAdapter
import com.unicorn.forensic2.ui.adapter.JdjgAdminCaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_case_list.*

class JdjgAdminCaseListFra : SimplePageFra<Case, KVHolder>() {

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
        val data = JdjgAdminCaseType.all.map { JdjgAdminCaseTypeS(it) }
        data.forEach { it.isSelect = it.jdjgAdminCaseType == jdjgAdminCaseType }
        jdjgAdminCaseTypeSAdapter.setNewData(data)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdjgAdminCaseType::class.java, Consumer {
            jdjgAdminCaseType = it
            loadFirstPage()
        })
    }

    override val simpleAdapter: BaseQuickAdapter<Case, KVHolder> = JdjgAdminCaseAdapter()

    private val jdjgAdminCaseTypeSAdapter = JdjgAdminCaseTypeSAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (jdjgAdminCaseType) {
            JdjgAdminCaseType.ZBTZ -> api.getZbtzList(page = page)
            JdjgAdminCaseType.DJD -> api.getDjdList(page = page)
            JdjgAdminCaseType.YJD -> api.getYjdList(page = page)
            JdjgAdminCaseType.YJJ -> api.getYjjList(page = page)
            JdjgAdminCaseType.YXA -> api.getYxaList(page = page)
            JdjgAdminCaseType.CLOSE -> api.getClose(page = page)
            JdjgAdminCaseType.UNCLOSE -> api.getUnClose(page = page)
        }
    }

    private var jdjgAdminCaseType = JdjgAdminCaseType.default

    //

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.fra_case_list
}