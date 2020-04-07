package com.unicorn.forensic2.ui.fra

import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.event.CaseQueryMapEvent
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
            CaseType.Jg -> api.getCaseJgList(page = page, queryMap = queryMap)
            CaseType.Dsr -> api.getCaseDsrList(page = page, queryMap = queryMap)
            CaseType.Zj -> api.getCaseZjList(page = page, queryMap = queryMap)
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
        RxBus.registerEvent(this, CaseQueryMapEvent::class.java, Consumer {
            queryMap = it.queryMap
            loadFirstPage()
        })
    }

    private var queryMap = HashMap<String, Any>()

}