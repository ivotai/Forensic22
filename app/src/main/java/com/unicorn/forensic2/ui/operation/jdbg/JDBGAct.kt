package com.unicorn.forensic2.ui.operation.jdbg

import android.os.Environment.getExternalStorageDirectory
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.files.fileChooser
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.JdLotteryTime
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdbg.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JDBGAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("鉴定报告")
        case.fidjdbgInfo?.let { tvJdbg.text = "鉴定报告" }
        case.fidjdwdInfo?.let { tvJdwd.text = "其他材料" }
        case.jdLotteryTime?.run {
            tvDaysAppraisal.setText(daysAppraisal.toString())
            tvDaysPay.setText(daysPay.toString())
            tvDaysClearfee.setText(daysClearfee.toString())
            tvDaysCheck.setText(daysCheck.toString())
            tvDaysEvidence.setText(daysEvidence.toString())
        }
        tvReportNo.setText(case.reportNo)
        tvRemark.setText(case.remark)
    }

    private var fid_jdbg: File? = null
    private var fid_jdwd: File? = null

    override fun bindIntent() {
        tvJdbg.safeClicks().subscribe { case.fidjdbgInfo?.open(this) }
        tvJdwd.safeClicks().subscribe { case.fidjdwdInfo?.open(this) }

        // 已鉴定 不允许编辑
        if (caseType == CaseType.YJD){
            tvDaysAppraisal.isEnabled = false
            tvDaysPay.isEnabled = false
            tvDaysClearfee.isEnabled = false
            tvDaysCheck.isEnabled = false
            tvDaysEvidence.isEnabled = false
            tvReportNo.isEnabled = false
            tvRemark.isEnabled = false
            tvDaysAppraisal.hint = ""
            tvDaysPay.hint = ""
            tvDaysClearfee.hint = ""
            tvDaysCheck.hint = ""
            tvDaysEvidence.hint = ""
            tvReportNo.hint = ""
            tvRemark.hint = ""
        }

        // 假如是已鉴定什么都不能保存
        if (caseType == CaseType.YJD) return
        titleBar.setOperation("保存").safeClicks().subscribe {
            if (fid_jdbg == null) {
                ToastUtils.showShort("请选择鉴定报告")
                return@subscribe
            }
            if (fid_jdwd == null) {
                ToastUtils.showShort("请选择其他材料")
                return@subscribe
            }
            if (tvDaysAppraisal.isEmpty()) {
                ToastUtils.showShort("鉴定所用时效不能为空")
                return@subscribe
            }
            if (tvDaysPay.isEmpty()) {
                ToastUtils.showShort("收到费用所用时效不能为空")
                return@subscribe
            }
            if (tvDaysClearfee.isEmpty()) {
                ToastUtils.showShort("报价所用时效不能为空")
                return@subscribe
            }
            if (tvDaysCheck.isEmpty()) {
                ToastUtils.showShort("查勘所用时效不能为空")
                return@subscribe
            }
            if (tvDaysEvidence.isEmpty()) {
                ToastUtils.showShort("补充证据所用时效不能为空")
                return@subscribe
            }
            if (tvReportNo.isEmpty()) {
                ToastUtils.showShort("报告编号不能为空")
                return@subscribe
            }
            save()
        }
        upJdbg.safeClicks().subscribe {
            val initialFolder = File(getExternalStorageDirectory(), "Download")
            MaterialDialog(this@JDBGAct).show {
                fileChooser(context = this@JDBGAct, initialDirectory = initialFolder) { _, file ->
                    fid_jdbg = file
                    this@JDBGAct.tvJdbg.text = file.name
                }
            }
        }
        upJdwd.safeClicks().subscribe {
            val initialFolder = File(getExternalStorageDirectory(), "Download")
            MaterialDialog(this@JDBGAct).show {
                fileChooser(context = this@JDBGAct, initialDirectory = initialFolder) { _, file ->
                    fid_jdwd = file
                    this@JDBGAct.tvJdwd.text = file.name
                }
            }
        }
    }

    private fun save() {
        val map = HashMap<String, RequestBody>()
        // 缺少了 lid 好像还保存错误...
        case.lid?.let { map["lid"] = it.toRequestBody(TextOrPlain) }
        map["reportNo"] = tvReportNo.trimText().toRequestBody(TextOrPlain)
        map["remark"] = tvRemark.trimText().toRequestBody(TextOrPlain)
        val jdLotteryTime = JdLotteryTime(
            caseId = case.caseId,
            lid = case.lid,
            daysAppraisal = tvDaysAppraisal.trimText().toInt(),
            daysPay = tvDaysPay.trimText().toInt(),
            daysClearfee = tvDaysClearfee.trimText().toInt(),
            daysCheck = tvDaysCheck.trimText().toInt(),
            daysEvidence = tvDaysEvidence.trimText().toInt()
        )
        map["jdLotteryTime"] = Gson().toJson(jdLotteryTime).toRequestBody(TextOrPlain)
        val part1 = MultipartBody.Part.createFormData(
            "fid_jdbg",
            fid_jdbg!!.name,
            fid_jdbg!!.asRequestBody("*/*".toMediaType())
        )
        val part2 = MultipartBody.Part.createFormData(
            "fid_jdwd",
            fid_jdwd!!.name,
            fid_jdwd!!.asRequestBody("*/*".toMediaType())
        )
        val mask = DialogHelper.showMask(this)
        v1Api.jdLotterySubmitResult(map, part1, part2)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success) {
                        ToastUtils.showShort("保存失败")
                        return@subscribeBy
                    }
                    ToastUtils.showShort("保存成功")
                    finish()
                    RxBus.post(RefreshEvent())
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("保存失败")
                }
            )
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }
    private val caseType by lazy { intent.getSerializableExtra(com.unicorn.forensic2.app.CaseType) as CaseType }
    override val layoutId = R.layout.act_jdbg

}
