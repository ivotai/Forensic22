package com.unicorn.forensic2.ui.operation

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.operation.hf.RefreshCaseEvent
import com.unicorn.forensic2.ui.operation.taskDoc.JdTaskDocParam
import io.reactivex.rxkotlin.subscribeBy

object JdTaskDocHelper {

    fun showJdTaskDocDialog(
        context: Context,
        case: Case,
        operation: String,
        taskType1: Int,
        taskType2: Int
    ) {
        MaterialDialog(context).show {
            input(allowEmpty = true, hint = "输入备注")
            title(text = operation)
            positiveButton(text = "同意") {
                jdTaskDoc(
                    context = context,
                    case = case,
                    taskType = taskType1,
                    operation = operation,
                    remark = it.getInputField().trimText()
                )
            }
            negativeButton(text = "不同意") {
                jdTaskDoc(
                    context = context,
                    case = case,
                    taskType = taskType2,
                    operation = operation,
                    remark = it.getInputField().trimText()
                )
            }
        }
    }

    //
    private fun jdTaskDoc(
        context: Context,
        case: Case,
        taskType: Int,
        operation: String,
        remark: String = ""
    ) {
        val mask = DialogHelper.showMask(context)
        val jdTaskDocParam =
            JdTaskDocParam(
                lid = case.lid,
                caseId = case.caseId,
                taskType = taskType,
                remark = remark
            )
        ComponentHolder.appComponent.v1Api().jdTaskDoc(jdTaskDocParam)
            .observeOnMain(context as LifecycleOwner)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("${operation}失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("${operation}成功")
                    RxBus.post(RefreshCaseEvent())
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("${operation}失败")
                }
            )
    }

}