package com.unicorn.forensic2.ui.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.defaultPageSize
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Page
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_swipe_recycler.*

abstract class SimplePageFra<Model, K : BaseViewHolder> : BaseFra() {

    abstract val simpleAdapter: BaseQuickAdapter<Model, K>

    abstract fun loadPage(page: Int): Single<Page<Model>>

    private val total
        get() = simpleAdapter.data.size

    private val pageNo
        get() = total / defaultPageSize + 1

    protected open val mRecyclerView: RecyclerView get() = recyclerView

    protected open val mSwipeRefreshLayout: SwipeRefreshLayout get() = swipeRefreshLayout

    override fun initViews() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            simpleAdapter.setEnableLoadMore(true)
        }
    }

    override fun bindIntent() {
        mSwipeRefreshLayout.setOnRefreshListener { loadFirstPage() }
        simpleAdapter.setOnLoadMoreListener({ loadNextPage() }, mRecyclerView)
        loadFirstPage()
    }

    protected fun loadFirstPage() {
        mSwipeRefreshLayout.isRefreshing = true
        loadPage(1)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mSwipeRefreshLayout.isRefreshing = false
                    simpleAdapter.setNewData(it.content)
                    checkIsLoadAll(it)
                },
                onError = {
                    mSwipeRefreshLayout.isRefreshing = false
                }
            )
    }

    private fun loadNextPage() {
        loadPage(pageNo)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    simpleAdapter.loadMoreComplete()
                    simpleAdapter.addData(it.content)
                    checkIsLoadAll(it)
                },
                onError = {
                    simpleAdapter.loadMoreComplete()
                }
            )
    }

    private fun checkIsLoadAll(page: Page<Model>) {
        val isLoadAll = total >= page.totalElements.toInt() // more safe but not exact
        if (isLoadAll) simpleAdapter.loadMoreEnd(true)
    }

    override val layoutId = R.layout.ui_swipe_recycler

}