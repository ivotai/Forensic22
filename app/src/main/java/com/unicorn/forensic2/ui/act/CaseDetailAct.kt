package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseProcess
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseProcessAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.operation.JdTaskDocHelper
import com.unicorn.forensic2.ui.operation.hf.HfAct
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import com.unicorn.forensic2.ui.operation.jdfk.JDFKAct
import com.unicorn.forensic2.ui.operation.lotteryDelay.LotteryDelayListAct
import com.unicorn.forensic2.ui.other.CaseDetailHeader
import io.reactivex.Single
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class CaseDetailAct : SimplePageAct<CaseProcess, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("案件详情")
        mRecyclerView.addDefaultItemDecoration(1)
        simpleAdapter.addHeaderView(CaseDetailHeader(this, case))
    }

    override fun bindIntent() {
        super.bindIntent()

        //
        if (Operation.all.isNotEmpty())
            titleBar.setOperation("操作").safeClicks().subscribe { showOperationDialog() }
    }

    private fun showOperationDialog() {
        MaterialDialog(this).show {
            listItems(items = Operation.all.map { it.cn }) { dialog, index, text ->
                val result = Operation.all[index]
                when (result) {
                    Operation.HF -> Intent(this@CaseDetailAct, HfAct::class.java).apply {
                        putExtra(Param, case.lid)
                    }.let { startActivity(it) }
                    Operation.JDFK -> Intent(this@CaseDetailAct, JDFKAct::class.java).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }
                    Operation.AJBW, Operation.BATX, Operation.JDBG -> ToastUtils.showShort("尚未实现")
                    Operation.BGPF_JDJGADMIN, Operation.BGPF_SFJD -> Intent(
                        this@CaseDetailAct,
                        LotteryDelayListAct::class.java
                    ).apply {
                        putExtra(Param, case)
                    }.let { startActivity(it) }

                    Operation.LASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 23, 24
                    )
                    Operation.CYHSP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 25, 26
                    )
                    Operation.JASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 27, 28
                    )
                    Operation.XASP -> JdTaskDocHelper.showJdTaskDocDialog(
                        this@CaseDetailAct,
                        case, result.cn, 29, 30
                    )
                    else -> ""
                }
            }
        }
    }

    //


    //

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshCaseEvent::class.java, Consumer {
            finish()
        })
    }

    //

    override val simpleAdapter = CaseProcessAdapter()

    override fun loadPage(page: Int): Single<Page<CaseProcess>> =
        v1Api.getCaseProcessList(caseId = case.caseId, page = page)

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}