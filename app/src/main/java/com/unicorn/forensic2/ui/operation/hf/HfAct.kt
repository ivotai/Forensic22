package com.unicorn.forensic2.ui.operation.hf

import android.view.View
import android.widget.RadioButton
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_hf.*

class HfAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("回复")
    }

    override fun bindIntent() {
        rgIsAccept.checkedChanges().subscribe {
            val radioButton = rgIsAccept.findViewById<RadioButton>(it)
            val flag = radioButton.text == "不接受"
            etRejectInfo.visibility = if (flag) View.VISIBLE else View.GONE
        }

        titleBar.setOperation("确定").safeClicks().subscribe {
            reply()
        }
    }

    private fun reply() {
        val mask = DialogHelper.showMask(this)
        val replyParam = ReplyParam(
            lid = lid,
            isAccept = if (rbAccept.isChecked) 1 else 0,
            rejectInfo = etRejectInfo.trimText()
        )
        v1Api.reply(replyParam).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("回复失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("回复成功")
                    RxBus.post(RefreshCaseEvent())
                    finish()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("回复失败")
                }
            )
    }

    private val lid by lazy { intent.getStringExtra(Param) }

    override val layoutId = R.layout.act_hf

}