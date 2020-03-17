package com.unicorn.forensic2.ui.act.addOrEdit

import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.param.addOrEdit.JdryMyEditParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdry_my_add_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JdryMyEditPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传鉴定人员照片")
        PictureHelper.load(this,"$pictureBaseUrl${jdryMyEditParam.fidzyzs}").into(ivFidzyzsNew)
        PictureHelper.load(this,"$pictureBaseUrl${jdryMyEditParam.fidzczs}").into(ivFidzczsNew)
    }

    override fun bindIntent() {
        ivFidzyzsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdryMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdryMyEditParam.fidzyzs_new = realPath
                        Glide.with(this@JdryMyEditPictureAct).load(realPath).into(ivFidzyzsNew)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivFidzczsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdryMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdryMyEditParam.fidzczs_new = realPath
                        Glide.with(this@JdryMyEditPictureAct).load(realPath).into(ivFidzczsNew)
                    }

                    override fun onCancel() {
                    }
                })
        }

        fun save() = with(jdryMyEditParam) {
            val map = HashMap<String, RequestBody>()
            map["xm"] = xm.toRequestBody(TextOrPlain)
            map["zjlx"] = zjlx.toRequestBody(TextOrPlain)
            map["zjhm"] = zjhm.toRequestBody(TextOrPlain)
            map["zczyh"] = zczyh.toRequestBody(TextOrPlain)
            map["grzc"] = grzc.toRequestBody(TextOrPlain)
            map["mphone"] = mphone.toRequestBody(TextOrPlain)
            map["jdlbId"] = jdlbId.toRequestBody(TextOrPlain)
            map["zyzsyxq"] = zyzsyxq.toRequestBody(TextOrPlain)
            var partZyzs:MultipartBody.Part?=null
            var partZczs:MultipartBody.Part?=null
            if (fidzyzs_new.isNotBlank()) {
                partZyzs = MultipartBody.Part.createFormData(
                    "fidzyzs_new",
                    "",
                    File(fidzyzs_new).asRequestBody("image/*".toMediaType())
                )
            }
            if (fidzczs_new.isNotBlank()) {
                partZczs = MultipartBody.Part.createFormData(
                    "fidzczs_new",
                    "",
                    File(fidzczs_new).asRequestBody("image/*".toMediaType())
                )
            }
            val mask = DialogHelper.showMask(this@JdryMyEditPictureAct)
            v1Api.editJdry(jdryMyEditParam.objectId,map, partZyzs, partZczs)
                .observeOnMain(this@JdryMyEditPictureAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("保存鉴定人员失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("保存鉴定人员成功")
                        finish()
                        RxBus.post(RefreshEvent())
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("保存鉴定人员失败")
                    }
                )
        }

        titleBar.setOperation("保存").safeClicks().subscribe { save() }
    }

    private val jdryMyEditParam by lazy { intent.getSerializableExtra(Param) as JdryMyEditParam }

    override val layoutId = R.layout.act_jdry_my_add_picture

}