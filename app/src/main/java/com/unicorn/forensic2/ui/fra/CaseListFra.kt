package com.unicorn.forensic2.ui.fra

import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single
import io.reactivex.functions.Consumer

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    private var caseType = CaseType.default

    override val simpleAdapter = CaseAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (caseType) {
            CaseType.Jg -> api.getCaseJgList(page)
            CaseType.Dsr -> api.getCaseDsrList(page)
            CaseType.Zj -> api.getCaseZjList(page)
            CaseType.None -> Single.create {
                Page<Case>(
                    content = ArrayList(),
                    totalElements = 0.toString()
                )
            }
        }
    }

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, CaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
    }

}