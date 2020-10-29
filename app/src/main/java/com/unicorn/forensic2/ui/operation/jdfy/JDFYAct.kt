package com.unicorn.forensic2.ui.operation.jdfy

import android.content.Intent
import android.os.Environment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.files.fileChooser
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.operation.jdfk.JdrySListAct
import com.unicorn.forensic2.ui.operation.jdfk.JdrySelectEvent
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdfy.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.joda.time.DateTime
import java.io.File

class JDFYAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("鉴定费用")
        case.fidsfbzInfo?.let { tvSfbz.text = "收费标准" }
        case.fidjfqdInfo?.let { tvJfqd.text = "缴费通知书" }

        // 其他值初始化
        tvFee.setText(case.fee?.toString() ?: "0")
        case.planFinish?.let { tvDelayTo.text = DateTime(it).toString("yyyy-MM-dd") }
    }

    private var fid_sfbz: File? = null
    private var fid_jfqd: File? = null
    private var jdryxm = ""
    private var jdryid = ""

    override fun bindIntent() {
        tvSfbz.safeClicks().subscribe { case.fidsfbzInfo?.open(this) }
        tvJfqd.safeClicks().subscribe { case.fidjdwdInfo?.open(this) }
        tvDelayTo.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    val dateTime = DateTime(date.time)
                    this@JDFYAct.tvDelayTo.text = dateTime.toString("yyyy-MM-dd")
                }
            }
        }
        tvJdry.safeClicks().subscribe {
            Intent(this, JdrySListAct::class.java).apply {
                putExtra(Param, case)
            }.let { startActivity(it) }
        }
        titleBar.setOperation("保存").safeClicks().subscribe {
            if (fid_sfbz == null) {
                ToastUtils.showShort("收费标准")
                return@subscribe
            }
            if (fid_jfqd == null) {
                ToastUtils.showShort("缴费通知书")
                return@subscribe
            }
            if (tvFee.isEmpty()) {
                ToastUtils.showShort("鉴定费用金额不能为空")
                return@subscribe
            }
            if (tvDelayTo.isEmpty()) {
                ToastUtils.showShort("鉴定完成时间不能为空")
                return@subscribe
            }
            if (tvJdry.isEmpty()) {
                ToastUtils.showShort("鉴定人姓名不能为空")
                return@subscribe
            }
            save()
        }
        upSfbz.safeClicks().subscribe {
            MaterialDialog(this).show {
                listItems(items = listOf("相册", "文件管理")) { dialog, index, text ->
                    if (index == 0) {
                        PictureHelper.selectPicture(
                            this@JDFYAct,
                            object : OnResultCallbackListener {
                                override fun onResult(result: MutableList<LocalMedia>) {
                                    val realPath = PictureHelper.getPath(result)
                                    fid_sfbz = File(realPath)
                                    this@JDFYAct.tvSfbz.text = File(realPath).name
                                }

                                override fun onCancel() {
                                }
                            })
                    }
                    if (index == 1) {
                        val initialFolder =
                            File(Environment.getExternalStorageDirectory(), "Download")
                        MaterialDialog(this@JDFYAct).show {
                            fileChooser(
                                context = this@JDFYAct,
                                initialDirectory = initialFolder
                            ) { _, file ->
                                fid_sfbz = file
                                this@JDFYAct.tvSfbz.text = file.name
                            }
                        }
                    }
                }
            }
        }
        upJfqd.safeClicks().subscribe {
            MaterialDialog(this).show {
                listItems(items = listOf("相册", "文件管理")) { dialog, index, text ->
                    if (index == 0) {
                        PictureHelper.selectPicture(
                            this@JDFYAct,
                            object : OnResultCallbackListener {
                                override fun onResult(result: MutableList<LocalMedia>) {
                                    val realPath = PictureHelper.getPath(result)
                                    fid_jfqd = File(realPath)
                                    this@JDFYAct.tvJfqd.text = File(realPath).name
                                }

                                override fun onCancel() {
                                }
                            })
                    }
                    if (index == 1) {
                        val initialFolder =
                            File(Environment.getExternalStorageDirectory(), "Download")
                        MaterialDialog(this@JDFYAct).show {
                            fileChooser(
                                context = this@JDFYAct,
                                initialDirectory = initialFolder
                            ) { _, file ->
                                fid_jfqd = file
                                this@JDFYAct.tvJfqd.text = file.name
                            }
                        }
                    }
                }
            }
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdrySelectEvent::class.java, Consumer { event ->
            jdryid = event.jdryList.joinToString { it.jdryid }
            jdryxm = event.jdryList.joinToString { it.xm }
            tvJdry.text = jdryxm
        })
    }

    private fun save() {
        val map = HashMap<String, RequestBody>()
        // 缺少了 lid 好像还保存错误...
        case.lid?.let { map["lid"] = it.toRequestBody(TextOrPlain) }
        map["fee"] = tvFee.trimText().toRequestBody(TextOrPlain)
        map["delayTo"] = tvDelayTo.trimText().toRequestBody(TextOrPlain)
        map["jdryid"] = jdryid.toRequestBody(TextOrPlain)
        map["jdryxm"] = jdryxm.toRequestBody(TextOrPlain)
        map["applyInfo"] = tvApplyInfo.trimText().toRequestBody(TextOrPlain)
        val terminate = if (rbAccept.isChecked) 1 else 0
        map["terminate"] = terminate.toString().toRequestBody(TextOrPlain)
        map["remark"] = tvRemark.trimText().toRequestBody(TextOrPlain)
        val part1 = MultipartBody.Part.createFormData(
            "fid_sfbz",
            fid_sfbz!!.name,
            fid_sfbz!!.asRequestBody("*/*".toMediaType())
        )
        val part2 = MultipartBody.Part.createFormData(
            "fid_jfqd",
            fid_jfqd!!.name,
            fid_jfqd!!.asRequestBody("*/*".toMediaType())
        )
        val mask = DialogHelper.showMask(this)
        v1Api.jdLotteryAddLotteryDelay(map, part1, part2)
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
    override val layoutId = R.layout.act_jdfy

}