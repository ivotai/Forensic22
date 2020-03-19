package com.unicorn.forensic2.ui.fra

import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    private var caseType = CaseType.Dsr

    override val simpleAdapter = CaseAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> = api.getDjdList(page = page)

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }

}