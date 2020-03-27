package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Fy
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.TreeFetcher2
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.TreeNode2
import com.unicorn.forensic2.data.model.Fy
import com.unicorn.forensic2.data.model.TreeResult2
import com.unicorn.forensic2.ui.adapter.TreeNodeAdapter2
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

class FyTreeAct : BaseAct() {

    private val treeFetcher2  = object : TreeFetcher2() {
        override fun getFirstLevel(): Single<List<Fy>> {
            return v1Api.getFy(fydm = "0")
        }

        override fun getChildren(fydm: String): Single<List<Fy>> {
            return v1Api.getFy(fydm = fydm)
        }
    }

    val title: String = "法院"

    val key: String = Fy

    private val simpleAdapter2 = TreeNodeAdapter2()

    override fun initViews() {
        titleBar.setTitle("选择$title")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FyTreeAct)
            simpleAdapter2.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
        simpleAdapter2.treeFetcher2 = treeFetcher2
    }


    override fun bindIntent() {
        fun getLevel0() {
            fun copeItems(item: List<Fy>) {
                Observable.fromIterable(item)
                    .map { TreeNode2(fy = it, nodeLevel = 1) as MultiItemEntity }
                    .toList()
                    .subscribeBy { simpleAdapter2.setNewData(it) }
            }

            val mask = DialogHelper.showMask(this)
            treeFetcher2.getFirstLevel()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        copeItems(it)
                    },
                    onError = {
                        mask.dismiss()
                    }
                )
        }
        getLevel0()
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeNode2::class.java, Consumer {
            RxBus.post(TreeResult2(treeNode2 = it, key = key))
            finish()
        })
    }

    override val layoutId = R.layout.ui_title_recycler

}