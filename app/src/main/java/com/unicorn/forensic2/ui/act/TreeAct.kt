package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.TreeFetcher
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Dict
import com.unicorn.forensic2.data.model.TreeNode
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.ui.adapter.TreeNodeAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

abstract class TreeAct : BaseAct() {

    abstract val treeFetcher: TreeFetcher

    abstract val title: String

    abstract val key: String

    private val simpleAdapter = TreeNodeAdapter()

    override fun initViews() {
        titleBar.setTitle("选择$title")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TreeAct)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
        simpleAdapter.treeFetcher = treeFetcher
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
            treeFetcher.getFirstLevel()
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

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeNode::class.java, Consumer {
            RxBus.post(TreeResult(treeNode = it, key = key))
            finish()
        })
    }

    override val layoutId = R.layout.ui_title_recycler

}