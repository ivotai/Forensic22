package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
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
                    Operation.AJBW, Operation.BATX -> ToastUtils.showShort("无")
                    Operation.BGPF_SFJD -> showBGPF_SFJDDialog()
                    Operation.LASP -> showLASPDialog()
                    else -> ""
                }
            }
        }
    }

    //

    private fun showBGPF_SFJDDialog() {
        MaterialDialog(this).show {
            title(text = Operation.BGPF_SFJD.cn)
            positiveButton(text = "同意") {
                jdTaskDoc(taskType = 21, operation = Operation.BGPF_SFJD.cn)

            }
            negativeButton(text = "不同意") {
                jdTaskDoc(taskType = 22, operation = Operation.BGPF_SFJD.cn)
            }
        }
    }

    private fun showLASPDialog() {
        MaterialDialog(this).show {
            title(text = Operation.LASP.cn)
            positiveButton(text = "同意") {
                jdTaskDoc(taskType = 23, operation = Operation.BGPF_SFJD.cn)

            }
            negativeButton(text = "不同意") {
                jdTaskDoc(taskType = 24, operation = Operation.BGPF_SFJD.cn)
            }
        }
    }

    //
    private fun jdTaskDoc(taskType: Int, operation: String) {
        val mask = DialogHelper.showMask(this)
        val jdTaskDocParam =
            JdTaskDocParam(lid = case.lid, caseId = case.caseId, TaskType = taskType)
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