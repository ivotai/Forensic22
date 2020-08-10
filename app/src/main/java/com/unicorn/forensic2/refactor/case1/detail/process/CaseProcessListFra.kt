package com.unicorn.forensic2.refactor.case1.detail.process

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single

class CaseProcessListFra : SimplePageFra<CaseProcess, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
//        mRecyclerView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.md_grey_200))
//        mRecyclerView.addItemDecoration(LinearSpanDecoration(ConvertUtils.dp2px(16f)))
    }

    override fun bindIntent() {
        super.bindIntent()
    }


    override val simpleAdapter: BaseQuickAdapter<CaseProcess, KVHolder> = CaseProcessAdapter()

    override fun loadPage(page: Int): Single<Page<CaseProcess>> =
        api.getCaseProcessList(caseId = case.caseId, page = page)

    private val case by lazy { arguments?.getSerializable(Param) as Case }

}