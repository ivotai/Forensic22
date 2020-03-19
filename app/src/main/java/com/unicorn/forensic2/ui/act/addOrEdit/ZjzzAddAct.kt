package com.unicorn.forensic2.ui.act.addOrEdit

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.widget.textChanges
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.addOrEdit.ZjzzAddParam
import com.unicorn.forensic2.ui.act.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_zjzz_add_or_edit.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ZjzzAddAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("添加专家资质")
    }

    override fun bindIntent() {
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        etGrzc.textChanges().map { it.toString() }.subscribe { zjzzAddParam.grzc = it }
        etZczyh.textChanges().map { it.toString() }.subscribe { zjzzAddParam.zczyh = it }
        tvZyzsyxq.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    val dateStr = date.time.time.toDisplayFormat()
                    zjzzAddParam.zyzsyxq = dateStr
                    this@ZjzzAddAct.tvZyzsyxq.text = dateStr
                }
            }
        }
        ivZyzs.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@ZjzzAddAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        zjzzAddParam.fid_zyzs = realPath
                        Glide.with(this@ZjzzAddAct).load(realPath).into(ivZyzs)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivZczs.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@ZjzzAddAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        zjzzAddParam.fid_zczs = realPath
                        Glide.with(this@ZjzzAddAct).load(realPath).into(ivZczs)
                    }

                    override fun onCancel() {
                    }
                })
        }

        fun save() = with(zjzzAddParam) {
            val map = HashMap<String, RequestBody>()
            map["jdlbId"] = jdlbId.toRequestBody(TextOrPlain)
            map["zczyh"] = zczyh.toRequestBody(TextOrPlain)
            map["grzc"] = grzc.toRequestBody(TextOrPlain)
            map["zyzsyxq"] = zyzsyxq.toRequestBody(TextOrPlain)

            var part1: MultipartBody.Part? = null
            var part2: MultipartBody.Part? = null
            if (fid_zyzs.isNotBlank()) {
                part1 = MultipartBody.Part.createFormData(
                    "fid_zyzs",
                    File(fid_zyzs).name,
                    File(fid_zyzs).asRequestBody("image/*".toMediaType())
                )
            }

            if (fid_zczs.isNotBlank()) {
                part2 = MultipartBody.Part.createFormData(
                    "fid_zczs",
                    File(fid_zczs).name,
                    File(fid_zczs).asRequestBody("image/*".toMediaType())
                )
            }
            val mask = DialogHelper.showMask(this@ZjzzAddAct)
            v1Api.addZjzz(map, part1, part2)
                .observeOnMain(this@ZjzzAddAct)
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

        titleBar.setOperation("保存").safeClicks().subscribe {
            with(zjzzAddParam) {
                if (jdlbId.isBlank()) {
                    ToastUtils.showShort("请选择鉴定类别")
                    return@subscribe
                }
                if (zczyh.isBlank()) {
                    ToastUtils.showShort("请选择注册执业号")
                    return@subscribe
                }
                if (grzc.isBlank()) {
                    ToastUtils.showShort("请选择个人职称")
                    return@subscribe
                }
                if (zyzsyxq.isBlank()) {
                    ToastUtils.showShort("请选择有效日期")
                    return@subscribe
                }
            }
            save()
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            zjzzAddParam.jdlbId = it.dict.id
            tvJdlb.text = it.dict.name
        })
    }

    private val zjzzAddParam = ZjzzAddParam()

    override val layoutId = R.layout.act_zjzz_add_or_edit

}