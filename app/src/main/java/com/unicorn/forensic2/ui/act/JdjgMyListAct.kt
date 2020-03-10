package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.ui.adapter.JdjgMyAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my_list.*

class JdjgMyListAct : BaseAct() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构信息")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        fun getJdjgMyList() {
            val mask = DialogHelper.showMask(this)
            v1Api.getJdjgMyList()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        simpleAdapter.setNewData(it)
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("获取机构信息失败")
                    }
                )
        }
        getJdjgMyList()
    }

    private val simpleAdapter = JdjgMyAdapter()

    override val layoutId = R.layout.act_jdjg_my_list

}
