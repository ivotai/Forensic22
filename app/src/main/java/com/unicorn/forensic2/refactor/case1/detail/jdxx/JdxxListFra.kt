package com.unicorn.forensic2.refactor.case1.detail.jdxx

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseFra
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_swipe_recycler.*
import kotlinx.android.synthetic.main.ui_title_recycler.recyclerView

class JdxxListFra : BaseFra() {

    override fun initViews() {
        super.initViews()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context!!)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        getJdxx()
        swipeRefreshLayout.setOnRefreshListener { getJdxx() }
    }

    private fun getJdxx(){
        api.getJdxx(caseId = case.caseId, roleTag = role!!.roleTag).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    simpleAdapter.setNewData(it)
                    swipeRefreshLayout.isRefreshing = false
                },
                onError = {
                    swipeRefreshLayout.isRefreshing = false
                }
            )
    }

    private val simpleAdapter = JdxxAdapter()

    private val case by lazy { arguments?.getSerializable(Param) as Case }

    override val layoutId = R.layout.ui_swipe_recycler

}
