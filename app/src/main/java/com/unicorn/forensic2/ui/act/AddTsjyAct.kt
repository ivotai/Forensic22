package com.unicorn.forensic2.ui.act

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.AddTsjySuccessEvent
import com.unicorn.forensic2.data.model.param.AddTsjyParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_add_tsjy.*

class AddTsjyAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("提交投诉建议")

        GradientDrawable().apply {
            setStroke(1, ContextCompat.getColor(this@AddTsjyAct, R.color.md_grey_700))
            cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        }.let { etContent.background = it }
    }

    override fun bindIntent() {
        fun submit() {
            if (etContent.isEmpty()) {
                ToastUtils.showShort("建议不能为空")
                return
            }
            val mask = DialogHelper.showMask(this)
            v1Api.addTsjy(AddTsjyParam(content = etContent.trimText())).observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success){
                            ToastUtils.showShort("提交建议失败")
                            return@subscribeBy
                        }
                        RxBus.post(AddTsjySuccessEvent())
                        finish()
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("提交建议失败")
                    }
                )
        }
        titleBar.setOperation("提交").safeClicks().subscribe { submit() }
    }

    override val layoutId = R.layout.act_add_tsjy

}