package com.unicorn.forensic2.ui.operation.caseDemo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single

class CaseDemoPListAct : SimplePageAct<CaseDemo, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<CaseDemo, KVHolder> = CaseDemoPAdapter()

    override fun loadPage(page: Int): Single<Page<CaseDemo>> =
        v1Api.jdCaseMemo(page = page, caseId = case.caseId)

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}