package com.unicorn.forensic2.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.PageParam
import com.unicorn.forensic2.data.model.PageResponse
import com.unicorn.forensic2.data.model.SearchParamCase
import com.unicorn.forensic2.ui.adapter.CaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    override val simpleAdapter = CaseAdapter()

    override fun loadPage(pageNo: Int): Single<PageResponse<Case>> {
        return v1Api.getCaseList(PageParam(pageNo = pageNo, searchParam = SearchParamCase()))
    }

    override fun initViews() {
        titleBar.setTitle("案件列表", false)
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }


    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.ui_title_swipe_recycler

}