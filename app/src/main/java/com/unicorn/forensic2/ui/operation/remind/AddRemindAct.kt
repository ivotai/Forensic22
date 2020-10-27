package com.unicorn.forensic2.ui.operation.remind

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_remind_add.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class AddRemindAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("添加提醒")
    }

    override fun bindIntent() {
        titleBar.setOperation("确定").safeClicks().subscribe {
            addRemind()
        }
        tvFromFlag.safeClicks().subscribe { showDialog() }
    }

    private var mFromFlag = ""

    private fun showDialog() {
        val roles = if (role == Role.JdjgAdmin) listOf(Role.JdjgAdmin) else listOf(
            Role.JdjgAdmin,
            Role.Spry
        )
        MaterialDialog(this).show {
            listItems(items = roles.map { it.cn }) { _, index, _ ->
                val fromFlag = if (index == 0) "3" else "1"
                mFromFlag = fromFlag
                this@AddRemindAct.tvFromFlag.text = roles[index].cn
            }
        }
    }

    private fun addRemind() {
        if (mFromFlag.isEmpty()) {
            ToastUtils.showShort("请选择发送方")
            return
        }
        if (etRemark.isEmpty()) {
            ToastUtils.showShort("提醒内容不能为空")
            return
        }
        val mask = DialogHelper.showMask(this)
        val map = HashMap<String, RequestBody>()
        map["fromFlag"] = mFromFlag.toRequestBody(TextOrPlain)
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