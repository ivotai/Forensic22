package com.unicorn.forensic2.ui.operation.lotteryDelay

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.operation.JdTaskDocHelper
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

class LotteryDelayListAct : BaseAct() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle(Operation.BGPF_JDJGADMIN.cn)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@LotteryDelayListAct)
            lotteryDelayAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        val mask = DialogHelper.showMask(this)
        v1Api.lotteryDelay(lid = case.lid, caseId = case.caseId).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    lotteryDelayAdapter.setNewData(it)
                },
                onError = {
                    mask.dismiss()
                }
            )

        if (roleTag == Role.Sfjd.en) {
            titleBar.setOperation("操作").safeClicks().subscribe {
                JdTaskDocHelper.showJdTaskDocDialog(
                    this@LotteryDelayListAct,
                    case,
                    Operation.BGPF_SFJD.cn,
                    21,
                    22
                )
            }
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshCaseEvent::class.java, Consumer {
            finish()
        })
    }

    private val lotteryDelayAdapter = LotteryDelayAdapter()

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.ui_title_recycler

}
