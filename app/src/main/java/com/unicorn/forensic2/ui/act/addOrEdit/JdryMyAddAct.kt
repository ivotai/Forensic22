package com.unicorn.forensic2.ui.act.addOrEdit

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.addOrEdit.JdryMyAddParam
import com.unicorn.forensic2.ui.act.tree.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_jdry_my_add_or_edit.*

class JdryMyAddAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("添加鉴定人员")
    }

    override fun bindIntent() {
        fun showZjlxDialog() {
            MaterialDialog(this@JdryMyAddAct).show {
                listItems(items = zjlxList.map { it.name }) { _, index, _ ->
                    param.zjlx = zjlxList[index].id
                    this@JdryMyAddAct.tvZjlx.text = zjlxList[index].name
                }
            }
        }

        fun showDateDialog() {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    param.zyzsyxq = date.time.time.toDisplayFormat()
                    this@JdryMyAddAct.tvZyzsyxq.text = param.zyzsyxq
                }
            }
        }

        fun nextStep() = with(param) {
            if (xm.isBlank()) {
                ToastUtils.showShort("请输入姓名")
                return@with
            }
            if (zjlx.isBlank()) {
                ToastUtils.showShort("请选择证件类型")
                return@with
            }
            if (zjhm.isBlank()) {
                ToastUtils.showShort("请输入证件号码")
                return@with
            }
            if (grzc.isBlank()) {
                ToastUtils.showShort("请输入个人职称")
                return@with
            }
            if (zczyh.isBlank()) {
                ToastUtils.showShort("请输入执业证号")
                return@with
            }
            if (jdlbId.isBlank()) {
                ToastUtils.showShort("请选择鉴定类别")
                return@with
            }
            if (zyzsyxq.isBlank()) {
                ToastUtils.showShort("请选择有效日期")
                return@with
            }
            if (mphone.isBlank()) {
                ToastUtils.showShort("请输入手机号码")
                return@with
            }
            Intent(this@JdryMyAddAct, JdryMyAddPictureAct::class.java).apply {
                putExtra(Param, param)
            }.let { startActivity(it) }
        }
        tvZjlx.safeClicks().subscribe { showZjlxDialog() }
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        tvZyzsyxq.safeClicks().subscribe { showDateDialog() }
        etXm.textChanges().map { it.toString() }.subscribe { param.xm = it }
        etZjhm.textChanges().map { it.toString() }.subscribe { param.zjhm = it }
        etGrzc.textChanges().map { it.toString() }.subscribe { param.grzc = it }
        etZczyh.textChanges().map { it.toString() }.subscribe { param.zczyh = it }
        etMPhone.textChanges().map { it.toString() }.subscribe { param.mphone = it }
        rtvNextStep.safeClicks().subscribe { nextStep() }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Jdlb -> {
                    param.jdlbId = it.dict.id
                    tvJdlb.text = it.dict.name
                }
            }
        })
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            finish()
        })
    }

    private val param = JdryMyAddParam()

    override val layoutId = R.layout.act_jdry_my_add_or_edit

}