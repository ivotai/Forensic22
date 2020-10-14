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
import com.unicorn.forensic2.data.model.Jdry
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.addOrEdit.JdryMyEditParam
import com.unicorn.forensic2.ui.act.tree.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_jdry_my_add_or_edit.*

class JdryMyEditAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("编辑鉴定人员")
    }

    override fun bindIntent() {
        fun initEditParam() = with(jdry) {
            jdryMyEditParam = JdryMyEditParam(
                objectId = jdryid,
                xm = xm,
                zjlx = zjlx,
                zjhm = zjhm,
                zczyh = zczyh,
                grzc = grzc,
                mphone = mphone ?: "",
                jdlbId = jdlbId,
                zyzsyxq = zyzsyxq.toDisplayFormat(),
                fidzczs = fidzczs,
                fidzyzs = fidzyzs
            )
        }
        initEditParam()

        fun display() = with(jdryMyEditParam) {
            etXm.setText(xm)
            tvZjlx.text = zjlxList.find { it.id == zjlx }?.name
            etZjhm.setText(zjhm)
            etZczyh.setText(zczyh)
            etGrzc.setText(grzc)
            tvJdlb.text = jdry.jdlb
            tvZyzsyxq.text = zyzsyxq
            etMPhone.setText(mphone)
        }
        display()

        fun showZjlxDialog() {
            MaterialDialog(this@JdryMyEditAct).show {
                listItems(items = zjlxList.map { it.name }) { _, index, _ ->
                    jdryMyEditParam.zjlx = zjlxList[index].id
                    this@JdryMyEditAct.tvZjlx.text = zjlxList[index].name
                }
            }
        }

        fun showDateDialog() {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    jdryMyEditParam.zyzsyxq = date.time.time.toDisplayFormat()
                    this@JdryMyEditAct.tvZyzsyxq.text = jdryMyEditParam.zyzsyxq
                }
            }
        }

        fun nextStep() = with(jdryMyEditParam) {
            if (xm.isBlank()) {
                ToastUtils.showShort("请输入姓名")
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
            if (zyzsyxq.isBlank()) {
                ToastUtils.showShort("请选择有效日期")
                return@with
            }
            if (mphone.isBlank()) {
                ToastUtils.showShort("请输入手机号码")
                return@with
            }
            Intent(this@JdryMyEditAct, JdryMyEditPictureAct::class.java).apply {
                putExtra(Param, jdryMyEditParam)
            }.let { startActivity(it) }
        }
        tvZjlx.safeClicks().subscribe { showZjlxDialog() }
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        tvZyzsyxq.safeClicks().subscribe { showDateDialog() }
        etXm.textChanges().map { it.toString() }.subscribe { jdryMyEditParam.xm = it }
        etZjhm.textChanges().map { it.toString() }.subscribe { jdryMyEditParam.zjhm = it }
        etGrzc.textChanges().map { it.toString() }.subscribe { jdryMyEditParam.grzc = it }
        etZczyh.textChanges().map { it.toString() }.subscribe { jdryMyEditParam.zczyh = it }
        etMPhone.textChanges().map { it.toString() }.subscribe { jdryMyEditParam.mphone = it }
        rtvNextStep.safeClicks().subscribe { nextStep() }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Jdlb -> {
                    jdryMyEditParam.jdlbId = it.dict.id
                    tvJdlb.text = it.dict.name
                }
            }
        })
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            finish()
        })
    }

    private lateinit var jdryMyEditParam: JdryMyEditParam

    private val jdry by lazy { intent.getSerializableExtra(Param) as Jdry }

    override val layoutId = R.layout.act_jdry_my_add_or_edit

}