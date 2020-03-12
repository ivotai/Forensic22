package com.unicorn.forensic2.ui.act

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.JgzzAddParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jgzz_add.*

class JgzzAddAct : BaseAct() {

    private val param = JgzzAddParam()

    override fun initViews() {
        titleBar.setTitle("添加资质详情")
    }

    override fun bindIntent() {
        fun showZzdjDialog() {
            if (param.jdlbId == -1) {
                ToastUtils.showShort("请先选择鉴定类别")
                return
            }
            v1Api.getZzdj(jdlbId = param.jdlbId)
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JgzzAddAct).show {
                            listItems(items = it.map { it.name }) { _, index, _ ->
                                param.zzdjId = it[index].id
                                this@JgzzAddAct.tvZzdj.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取资质等级失败")
                    }
                )
        }

        fun showDateDialog() {
            MaterialDialog(this).show {
                datePicker { dialog, date ->
                    param.yxrq = date.time.time.toDisplayFormat()
                    this@JgzzAddAct.tvYxrq.text = param.yxrq
                }
            }
        }

        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        tvZzdj.safeClicks().subscribe { showZzdjDialog() }
        tvCyly.safeClicks().subscribe { startAct(CylyTreeAct::class.java) }
        tvYxrq.safeClicks().subscribe { showDateDialog() }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Jdlb -> {
                    param.jdlbId = it.dict.id
                    tvJdlb.text = it.dict.name
                }
                Cyly -> {
                    param.cylyId = it.dict.id
                    tvCyly.text = it.dict.name
                }
            }
        })
    }

    override val layoutId = R.layout.act_jgzz_add

}