package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.JdjgId
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_detail.*

class JdjgDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("机构详情")
    }

    override fun bindIntent() {
        getJdjgDetail()
    }

    private fun getJdjgDetail() {
        v1Api.getJdjgDetail(jdjgId = jdjgId)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                },
                onError = {
                    ToastUtils.showShort("获取机构详情失败")
                }
            )
    }

    private fun displayDetail() {

    }

//    lateinit var jdjg: Jdjg

    private val jdjgId by lazy { intent.getStringExtra(JdjgId) }

    override val layoutId = R.layout.act_jdjg_detail

}