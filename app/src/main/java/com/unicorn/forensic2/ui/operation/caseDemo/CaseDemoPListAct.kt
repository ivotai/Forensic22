package com.unicorn.forensic2.ui.operation.caseDemo

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_remind_add.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class CaseDemoPListAct : SimplePageAct<CaseDemo, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("案件备忘")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override fun bindIntent() {
        super.bindIntent()
        titleBar.setOperation("添加").safeClicks().subscribe { showAddCaseDemoDialog() }
    }

    override val simpleAdapter: BaseQuickAdapter<CaseDemo, KVHolder> = CaseDemoPAdapter()

    override fun loadPage(page: Int): Single<Page<CaseDemo>> =
        v1Api.jdCaseMemo(page = page, caseId = case.caseId)

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    fun showAddCaseDemoDialog() {
        val operation = "添加案件备忘"
        MaterialDialog(this).show {
            input(allowEmpty = false, hint = "输入备忘内容")
            title(text = operation)
            positiveButton(text = "同意") {
                addCaseDemo(content = it.getInputField().trimText(), operation = operation)
            }
        }
    }

    private fun addCaseDemo(content: String, operation: String) {
        val mask = DialogHelper.showMask(this)
        val map = HashMap<String, RequestBody>()
        map["caseId"] = case.caseId.toRequestBody(TextOrPlain)
        map["content"] = content.toRequestBody(TextOrPlain)
        v1Api.jdCaseMemo(map)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("${operation}失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("${operation}成功")
                    loadFirstPage()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("${operation}失败")
                }
            )
    }

}