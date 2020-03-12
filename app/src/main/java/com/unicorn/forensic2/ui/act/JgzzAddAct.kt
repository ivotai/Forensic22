package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Cyly
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jgzz_add.*

class JgzzAddAct : BaseAct() {

    private var jdlbId = -1
    private var zzdjId = -1

    override fun initViews() {
        titleBar.setTitle("添加资质详情")
    }

    override fun bindIntent() {
        fun showJdlbDialog() {
            v1Api.getJdlb()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JgzzAddAct).show {
                            listItems(items = it.map { it.name }) { _, index, _ ->
                                jdlbId = it[index].id
                                this@JgzzAddAct.tvJdlb.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取鉴定类别失败")
                    }
                )
        }
        tvJdlb.safeClicks().subscribe { showJdlbDialog() }

        fun showZzdjDialog() {
            v1Api.getZzdj(jdlbId = jdlbId)
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JgzzAddAct).show {
                            listItems(items = it.map { it.name }) { _, index, _ ->
                                zzdjId = it[index].id
                                this@JgzzAddAct.tvZzdj.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取资质等级失败")
                    }
                )
        }
        tvZzdj.safeClicks().subscribe {
            if (jdlbId == -1)
                ToastUtils.showShort("请先选择鉴定类别")
            else
                showZzdjDialog()
        }

        tvCyly.safeClicks().subscribe {
            Intent(this, CylyTreeAct::class.java).apply {
            }.let { startActivity(it) }
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Cyly -> {
                    tvCyly.text = it.treeNode.dict.name
                }
            }
        })
    }

    override val layoutId = R.layout.act_jgzz_add

}