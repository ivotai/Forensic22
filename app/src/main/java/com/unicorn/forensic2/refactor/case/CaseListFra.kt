package com.unicorn.forensic2.refactor.case

import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import io.reactivex.Single
import io.reactivex.functions.Consumer

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.md_grey_200))
        mRecyclerView.addItemDecoration(LinearSpanDecoration(ConvertUtils.dp2px(16f)))
    }

    override fun bindIntent() {
        super.bindIntent()
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, CaseQueryEvent::class.java, Consumer {
            queryMap = it.queryMap
            loadFirstPage()
        })
        RxBus.registerEvent(this, RefreshCaseEvent::class.java, Consumer {
            loadFirstPage()
        })
    }

    private var queryMap = HashMap<String, Any>()

    override val simpleAdapter: BaseQuickAdapter<Case, KVHolder> = CaseAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (caseType) {
            CaseType.ZBTZ -> api.getZbtzList(page = page, queryMap = queryMap)
            CaseType.DJD -> api.getDjdList(page = page, queryMap = queryMap)
            CaseType.YJD -> api.getYjdList(page = page, queryMap = queryMap)
            CaseType.YJJ -> api.getYjjList(page = page, queryMap = queryMap)
            CaseType.YQX -> api.getYxaList(page = page, queryMap = queryMap)
            CaseType.CLOSE -> api.getClose(page = page, queryMap = queryMap)
            CaseType.UNCLOSE -> api.getUnClose(page = page, queryMap = queryMap)
            CaseType.LASP -> api.getAcceptApproval(page = page, queryMap = queryMap)
            CaseType.CYHSP -> api.getShakeAgainApproval(page = page, queryMap = queryMap)
            CaseType.JASP -> api.getCloseApproval(page = page, queryMap = queryMap)
            CaseType.XASP -> api.getDestroyApproval(page = page, queryMap = queryMap)
        }
    }

    private val caseType by lazy { arguments?.getSerializable(Param) as CaseType }

}