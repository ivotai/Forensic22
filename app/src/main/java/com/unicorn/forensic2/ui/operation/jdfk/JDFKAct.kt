package com.unicorn.forensic2.ui.operation.jdfk

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdfk.*

class JDFKAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle(Operation.JDFK.cn)
    }

    override fun bindIntent() {
        tvJdryxm.safeClicks().subscribe {
            Intent(this@JDFKAct, JdrySListAct::class.java).apply {
                putExtra(Param, case)
            }.let { startActivity(it) }
        }
        tvDelayTo.safeClicks().subscribe { showDateDialog() }
        titleBar.setOperation("确定").safeClicks().subscribe {
            addLotteryDelay()
        }
    }

    private fun showDateDialog() {
        MaterialDialog(this).show {
            datePicker { _, date ->
                this@JDFKAct.tvDelayTo.text = date.time.time.toDisplayFormat()
            }
        }
    }

    private fun addLotteryDelay() {
        if (tvFee.isEmpty()) {
            ToastUtils.showShort("请输入鉴定费用金额")
            return
        }
        if (tvDelayTo.isEmpty()) {
            ToastUtils.showShort("请选择鉴定完成时间")
            return
        }
        if (tvJdryxm.isEmpty()) {
            ToastUtils.showShort("请选择鉴定人员")
            return
        }
        addLotteryDelay_()
    }

    private fun addLotteryDelay_() {
        val mask = DialogHelper.showMask(this)
        val addLotteryDelayParam =
            AddLotteryDelayParam(
                lid = case.lid!!,
                fee = tvFee.trimText().toInt(),
                delayTo = tvDelayTo.trimText(),
                jdryid = jdryList!!.map { it.jdryid },
                jdryxm = jdryList!!.map { it.xm },
                terminate = if (rb0.isChecked) 0 else 1
            )
        val operation = Operation.JDFK.cn
        v1Api.addLotteryDelay(addLotteryDelayParam).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("${operation}失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("${operation}成功")
                    RxBus.post(RefreshCaseEvent())
                    finish()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("${operation}失败")
                }
            )
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdrySelectEvent::class.java, Consumer { jdrySelectEvent ->
            jdryList = jdrySelectEvent.jdryList
            tvJdryxm.text = jdryList!!.joinToString(",") { it.xm }
        })
    }

    override val layoutId = R.layout.act_jdfk

    private var jdryList: List<JdrySimple>? = null

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}