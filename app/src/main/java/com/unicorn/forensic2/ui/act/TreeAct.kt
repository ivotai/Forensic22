package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Dict
import com.unicorn.forensic2.data.model.TreeNode
import com.unicorn.forensic2.ui.adapter.TreeNodeAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

class TreeAct : BaseAct() {

    private val simpleAdapter = TreeNodeAdapter()

    override fun initViews() {
        titleBar.setTitle("标题需要传递参数")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TreeAct)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        fun getLevel0() {
            fun copeDicts(dicts: List<Dict>) {
                Observable.fromIterable(dicts)
                    .map { TreeNode(dict = it, nodeLevel = 1) as MultiItemEntity }
                    .toList()
                    .subscribeBy { simpleAdapter.setNewData(it) }
            }

            val mask = DialogHelper.showMask(this)
            v1Api.getRegion()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        copeDicts(it)
                    },
                    onError = {
                        mask.dismiss()
                    }
                )
        }
        getLevel0()
    }

    override val layoutId = R.layout.ui_title_recycler

}