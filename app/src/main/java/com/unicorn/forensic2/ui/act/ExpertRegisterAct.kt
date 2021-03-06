package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.widget.textChanges
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.Expert
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_expert_edit_or_add.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ExpertRegisterAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("注册专家")
    }

    private fun getPersonalInfo() {
        v1Api.getPersonalInfo().observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    if (it.success) {
                        loginResult.user = it.user
                        RxBus.post(LoginStateChangeEvent())
                    }
                },
                onError = {
                }
            )
    }

    override fun bindIntent() {
        etExpertName.textChanges().map { it.toString() }.subscribe { expert.expertName = it }
        etSfzh.textChanges().map { it.toString() }.subscribe { expert.sfzh = it }
        etPhoneNumber.textChanges().map { it.toString() }.subscribe { expert.phoneNumber = it }
        etEmail.textChanges().map { it.toString() }.subscribe { expert.email = it }
        etAddr.textChanges().map { it.toString() }.subscribe { expert.addr = it }
        etZyms.textChanges().map { it.toString() }.subscribe { expert.zyms = it }

        ivFidphoto.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@ExpertRegisterAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = PictureHelper.getPath(result)
                        expert.fid_photo = realPath
                        Glide.with(this@ExpertRegisterAct).load(realPath).into(ivFidphoto)
                    }

                    override fun onCancel() {
                    }
                })
        }

        fun save() = with(expert) {
            val map = HashMap<String, RequestBody>()
            map["expertName"] = expertName.toRequestBody(TextOrPlain)
            map["sfzh"] = sfzh.toRequestBody(TextOrPlain)
            map["phoneNumber"] = phoneNumber.toRequestBody(TextOrPlain)
            map["email"] = email.toRequestBody(TextOrPlain)
            map["addr"] = addr.toRequestBody(TextOrPlain)
            map["zyms"] = zyms.toRequestBody(TextOrPlain)
            var part: MultipartBody.Part? = null
            if (fid_photo.isNotBlank()) {
                part = MultipartBody.Part.createFormData(
                    "fid_photo",
                    "",
                    File(fid_photo).asRequestBody("image/*".toMediaType())
                )
            }
            val mask = DialogHelper.showMask(this@ExpertRegisterAct)
            v1Api.registerExpert(map, part)
                .observeOnMain(this@ExpertRegisterAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("注册失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("注册成功")
                        getPersonalInfo()
                        finish()
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("注册失败")
                    }
                )
        }
        titleBar.setOperation("保存").safeClicks().subscribe {
            with(expert) {
                if (expertName.isBlank()) {
                    ToastUtils.showShort("请输入专家姓名")
                    return@with
                }
                if (sfzh.isBlank()) {
                    ToastUtils.showShort("请输入身份证号")
                    return@with
                }
                if (phoneNumber.isBlank()) {
                    ToastUtils.showShort("请输入手机号码")
                    return@with
                }
                if (email.isBlank()) {
                    ToastUtils.showShort("请输入邮箱")
                    return@with
                }
                if (addr.isBlank()) {
                    ToastUtils.showShort("请输入地址")
                    return@with
                }
                if (zyms.isBlank()) {
                    ToastUtils.showShort("请输入专业描述")
                    return@with
                }

                save()
            }
        }
    }

    private val expert = Expert()

    override val layoutId = R.layout.act_expert_edit_or_add

}