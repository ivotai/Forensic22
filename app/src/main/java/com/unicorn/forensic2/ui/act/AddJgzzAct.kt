package com.unicorn.forensic2.ui.act

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_add_jgzz.*

class AddJgzzAct : BaseAct() {

    private var jdlbId = ""

    override fun initViews() {
        titleBar.setTitle("添加资质详情")
    }

    override fun bindIntent() {
        fun showJdlbDialog() {
            v1Api.getJdlb()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@AddJgzzAct).show {
                            listItems(items = it.map { it.name }) { dialog, index, text ->
                                jdlbId = it[index].id
                                this@AddJgzzAct.tvJdlb.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取鉴定类别失败")
                    }
                )
        }
        tvJdlb.safeClicks().subscribe { showJdlbDialog() }
    }

    override val layoutId = R.layout.act_add_jgzz

}