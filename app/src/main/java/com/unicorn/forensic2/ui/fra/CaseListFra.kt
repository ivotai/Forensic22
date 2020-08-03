package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.CaseTypeS
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.act.query.CaseQueryAct
import com.unicorn.forensic2.ui.adapter.CaseAdapter
import com.unicorn.forensic2.ui.adapter.CaseTypeSAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_case_list.*

class CaseListFra : SimplePageFra<Case, KVHolder>() {

    override fun initViews() {
        super.initViews()

        titleBar.setTitle("我的案件", false)

        rvCaseTypeS.apply {
            layoutManager = LinearLayoutManager(context)
            caseTypeSAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        super.bindIntent()

        caseType = CaseType.default

        val data = CaseType.all.map { CaseTypeS(it) }
        data.forEach { it.isSelect = it.caseType == caseType }
        caseTypeSAdapter.setNewData(data)

        titleBar.setOperation("查询").safeClicks().subscribe {
            context?.startAct(CaseQueryAct::class.java)
        }

    }

    override fun registerEvent() {
        RxBus.registerEvent(this, CaseType::class.java, Consumer {
            caseType = it
            loadFirstPage()
        })
        RxBus.registerEvent(this, QueryMapEvent::class.java, Consumer {
            queryMap = it.queryMap
            loadFirstPage()
        })
    }

    private var queryMap = HashMap<String, Any>()

    override val simpleAdapter: BaseQuickAdapter<Case, KVHolder> = CaseAdapter()

    private val caseTypeSAdapter = CaseTypeSAdapter()

    override fun loadPage(page: Int): Single<Page<Case>> {
        return when (caseType) {
            CaseType.ZBTZ -> api.getZbtzList(page = page, queryMap = queryMap)
            CaseType.DJD -> api.getDjdList(page = page, queryMap = queryMap)
            CaseType.YJD -> api.getYjdList(page = page, queryMap = queryMap)
            CaseType.YJJ -> api.getYjjList(page = page, queryMap = queryMap)
            CaseType.YXA -> api.getYxaList(page = page, queryMap = queryMap)
            CaseType.CLOSE -> api.getClose(page = page, queryMap = queryMap)
            CaseType.UNCLOSE -> api.getUnClose(page = page, queryMap = queryMap)
            CaseType.LASP -> api.getAcceptApproval(page = page, queryMap = queryMap)
            CaseType.CYHSP -> api.getShakeAgainApproval(page = page, queryMap = queryMap)
            CaseType.JASP -> api.getCloseApproval(page = page, queryMap = queryMap)
            CaseType.XASP -> api.getDestroyApproval(page = page, queryMap = queryMap)
        }
    }

    companion object {
        var caseType = CaseType.default
    }

    //

    override val mRecyclerView: RecyclerView
        get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val layoutId = R.layout.fra_case_list

}