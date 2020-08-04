package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseProcess
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.CaseProcessAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.operation.hf.HfAct
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import com.unicorn.forensic2.ui.operation.lotteryDelay.LotteryDelayListAct
import com.unicorn.forensic2.ui.operation.taskDoc.JdTaskDocParam
import com.unicorn.forensic2.ui.other.CaseDetailHeader
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
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
                    Operation.AJBW, Operation.BATX -> ToastUtils.showShort("尚未实现")
                    Operation.BGPF_JDJGADMIN -> Intent(
                        this@CaseDetailAct,
                        LotteryDelayListAct::class.java
                    ).apply {
                        putExtra(Param, case.lid)
                    }.let { startActivity(it) }
                    Operation.BGPF_SFJD -> showJdTaskDocDialog(result.cn, 21, 22)
                    Operation.LASP -> showJdTaskDocDialog(result.cn, 23, 24)
                    Operation.CYHSP -> showJdTaskDocDialog(result.cn, 25, 26)
                    Operation.JASP -> showJdTaskDocDialog(result.cn, 27, 28)
                    Operation.XASP -> showJdTaskDocDialog(result.cn, 29, 30)
                    else -> ""
                }
            }
        }
    }

    //

    private fun showJdTaskDocDialog(operation: String, taskType1: Int, taskType2: Int) {
        MaterialDialog(this).show {
            input(allowEmpty = true, hint = "输入备注")
            title(text = operation)
            positiveButton(text = "同意") {
                jdTaskDoc(
                    taskType = taskType1,
                    operation = operation,
                    remark = it.getInputField().trimText()
                )
            }
            negativeButton(text = "不同意") {
                jdTaskDoc(
                    taskType = taskType2,
                    operation = operation,
                    remark = it.getInputField().trimText()
                )
            }
        }
    }

    //
    private fun jdTaskDoc(taskType: Int, operation: String, remark: String = "") {
        val mask = DialogHelper.showMask(this)
        val jdTaskDocParam =
            JdTaskDocParam(
                lid = case.lid,
                caseId = case.caseId,
                taskType = taskType,
                remark = remark
            )
        v1Api.jdTaskDoc(jdTaskDocParam).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("${operation}失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("${operation}批复成功")
                    RxBus.post(RefreshCaseEvent())
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("${operation}失败")
                }
            )
    }

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