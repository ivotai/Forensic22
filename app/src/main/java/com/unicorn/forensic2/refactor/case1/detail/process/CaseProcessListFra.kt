package com.unicorn.forensic2.refactor.case1.detail.process

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single

class CaseProcessListFra : SimplePageFra<CaseProcess, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.setBackgroundColor(Color.WHITE)
    }

    override fun bindIntent() {
        super.bindIntent()
    }

    override val simpleAdapter: BaseQuickAdapter<CaseProcess, KVHolder> = CaseProcessAdapter()

    override fun loadPage(page: Int): Single<Page<CaseProcess>> =
        api.getCaseProcessList(caseId = case.caseId, page = page)

    private val case by lazy { arguments?.getSerializable(Param) as Case }

}