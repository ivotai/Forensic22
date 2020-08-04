package com.unicorn.forensic2.ui.operation.lotteryDelay

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.ui.base.BaseAct
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
        v1Api.lotteryDelay(lid = lid).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    lotteryDelayAdapter.setNewData(it)
                },
                onError = {
                    mask.dismiss()
                }
            )
    }

    private val lotteryDelayAdapter = LotteryDelayAdapter()

    private val lid by lazy { intent.getStringExtra(Param) }

    override val layoutId = R.layout.ui_title_recycler

}
