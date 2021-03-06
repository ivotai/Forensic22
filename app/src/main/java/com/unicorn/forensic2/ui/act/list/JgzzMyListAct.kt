package com.unicorn.forensic2.ui.act.list

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.ui.act.JgzzAddAct
import com.unicorn.forensic2.ui.adapter.JgzzMyAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my_guide.*
import kotlinx.android.synthetic.main.ui_title_recycler.*
import kotlinx.android.synthetic.main.ui_title_recycler.titleBar

class JgzzMyListAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("资质信息")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        refresh()
        titleBar.setOperation("添加").safeClicks().subscribe { startAct(JgzzAddAct::class.java) }
    }

    private fun refresh() {
        val mask = DialogHelper.showMask(this)
        v1Api.getJdjgMy()
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    simpleAdapter.setNewData(it.jgzzList)
                },
                onError = {
                    mask.dismiss()
                    root.visibility = View.INVISIBLE
                    ToastUtils.showShort("获取机构信息失败")
                }
            )
    }

    override fun registerEvent() {

        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            refresh()
        })
    }

    private val simpleAdapter = JgzzMyAdapter()

    override val layoutId: Int = R.layout.ui_title_recycler

}