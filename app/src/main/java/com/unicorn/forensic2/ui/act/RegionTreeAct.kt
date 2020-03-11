package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Dict
import com.unicorn.forensic2.data.model.Region0
import com.unicorn.forensic2.ui.adapter.RegionAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.ui_title_recycler.*

class RegionTreeAct : BaseAct() {

    private val simpleAdapter = RegionAdapter()

    override fun initViews() {
        titleBar.setTitle("选择机构所在地")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RegionTreeAct)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {

        fun getRegion() {
            fun copeRegion(dicts: List<Dict>) {
                Observable.fromIterable(dicts)
                    .map { Region0(dict = it) as MultiItemEntity }
                    .toList()
                    .subscribeBy {
                        simpleAdapter.setNewData(it)
                    }
            }

            val mask = DialogHelper.showMask(this)
            v1Api.getRegion(0)
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        copeRegion(it)
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("获取机构所在地失败")
                    }
                )
        }
        getRegion()
    }

    override val layoutId = R.layout.ui_title_recycler

}