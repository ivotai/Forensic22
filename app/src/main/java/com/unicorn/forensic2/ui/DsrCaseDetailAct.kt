package com.unicorn.forensic2.ui

import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.DsrCase
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseProcess
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseProcessAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.other.CaseDetailHeader
import com.unicorn.forensic2.ui.other.DsrCaseDetailHeader
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class DsrCaseDetailAct : SimplePageAct<CaseProcess, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("案件详情")
        mRecyclerView.addDefaultItemDecoration(1)
        simpleAdapter.addHeaderView(DsrCaseDetailHeader(this, dsrCase))
    }

    override fun bindIntent() {
        super.bindIntent()
    }

    private val dsrCase by lazy { intent.getSerializableExtra(Param) as DsrCase }

    override val simpleAdapter = CaseProcessAdapter()

    override fun loadPage(page: Int): Single<Page<CaseProcess>> =
        v1Api.getCaseProcessList(caseId = dsrCase.caseId, page = page)

}