package com.unicorn.forensic2.ui.operation.remind

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_remind_add.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class AddRemindAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("添加提醒")
        tvFromFlag.text = "鉴定机构"
    }

    override fun bindIntent() {
        titleBar.setOperation("确定").safeClicks().subscribe {
            addRemind()
        }
    }

    private fun addRemind() {
        if (etRemark.isEmpty()) {
            ToastUtils.showShort("提醒内容不能为空")
            return
        }
        val mask = DialogHelper.showMask(this)
        val map = HashMap<String, RequestBody>()
        map["fromFlag"] = "3".toRequestBody(TextOrPlain)
        map["remark"] = etRemark.trimText().toRequestBody(TextOrPlain)
        map["toFlag"] = "2".toRequestBody(TextOrPlain)
        map["caseId"] = case.caseId.toRequestBody(TextOrPlain)
        if (case.jgid != null)
            map["jgId"] = case.jgid!!.toRequestBody(TextOrPlain)
        v1Api.jdRemind(map).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("添加提醒失败")
                        return@subscribeBy
                    }
                    RxBus.post(AddRemindSuccessEvent())
                    finish()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("添加提醒失败")
                }
            )
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.act_remind_add

}