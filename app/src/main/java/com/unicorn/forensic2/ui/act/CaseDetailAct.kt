package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseProcess
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseProcessAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.other.CaseDetailHeader
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class CaseDetailAct : SimplePageAct<CaseProcess, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("案件详情")
        mRecyclerView.addDefaultItemDecoration(1)
        simpleAdapter.addHeaderView(CaseDetailHeader(this,case))
    }

    override fun bindIntent() {
        super.bindIntent()
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val simpleAdapter = CaseProcessAdapter()

    override fun loadPage(page: Int): Single<Page<CaseProcess>> =
        v1Api.getCaseProcessList(caseId = case.caseId, page = page)

}