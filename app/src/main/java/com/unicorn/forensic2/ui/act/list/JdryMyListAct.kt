package com.unicorn.forensic2.ui.act.list

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.JdryMyListNeedRefreshEvent
import com.unicorn.forensic2.ui.act.addOrEdit.JdryMyAddAct
import com.unicorn.forensic2.ui.adapter.JdryMyAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my.*
import kotlinx.android.synthetic.main.ui_title_recycler.*
import kotlinx.android.synthetic.main.ui_title_recycler.titleBar

class JdryMyListAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("人员信息")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        refresh()
        titleBar.setOperation("添加").safeClicks().subscribe { startAct(JdryMyAddAct::class.java) }
    }

    private fun refresh() {
        val mask = DialogHelper.showMask(this)
        v1Api.getJdjgMy()
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    simpleAdapter.setNewData(it.jdryList)
                },
                onError = {
                    mask.dismiss()
                    root.visibility = View.INVISIBLE
                    ToastUtils.showShort("获取鉴定人员列表失败")
                }
            )
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdryMyListNeedRefreshEvent::class.java, Consumer {
            refresh()
        })
    }

    private val simpleAdapter = JdryMyAdapter()

    override val layoutId: Int = R.layout.ui_title_recycler

}