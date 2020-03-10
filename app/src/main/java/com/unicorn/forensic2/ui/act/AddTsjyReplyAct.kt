package com.unicorn.forensic2.ui.act

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.param.AddTsjyReplyParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_add_tsjy_reply.*

class AddTsjyReplyAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("回复投诉建议")

        GradientDrawable().apply {
            setStroke(1, ContextCompat.getColor(this@AddTsjyReplyAct, R.color.md_grey_700))
            cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        }.let { etReply.background = it }
    }

    override fun bindIntent() {
        fun submit() {
            if (etReply.isEmpty()) {
                ToastUtils.showShort("回复不能为空")
                return
            }
            val mask = DialogHelper.showMask(this)
            v1Api.addTsjyReply(
                tsjyId = intent.getStringExtra(TsjyId)!!,
                addTsjyReplyParam = AddTsjyReplyParam(reply = etReply.trimText())
            )
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("回复建议失败")
                            return@subscribeBy
                        }
                        finish()
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("回复建议失败")
                    }
                )
        }
        titleBar.setOperation("回复").safeClicks().subscribe { submit() }
    }

    override val layoutId = R.layout.act_add_tsjy_reply

}