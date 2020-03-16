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
import com.unicorn.forensic2.data.model.param.add.JdryMyAddParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdry_my_add_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JdryMyAddPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传人员详情照片")
    }

    override fun bindIntent() {
        ivFidzyzsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdryMyAddPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        param.fidzyzs_new = realPath
                        Glide.with(this@JdryMyAddPictureAct).load(realPath).into(ivFidzyzsNew)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivFidzczsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdryMyAddPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        param.fidzczs_new = realPath
                        Glide.with(this@JdryMyAddPictureAct).load(realPath).into(ivFidzczsNew)
                    }

                    override fun onCancel() {
                    }
                })
        }

        fun createJdry() = with(param) {
            val map = HashMap<String, RequestBody>()
            map["xm"] = xm.toRequestBody(TextOrPlain)
            map["zjlx"] = zjlx.toRequestBody(TextOrPlain)
            map["zjhm"] = zjhm.toRequestBody(TextOrPlain)
            map["zczyh"] = zczyh.toRequestBody(TextOrPlain)
            map["grzc"] = grzc.toRequestBody(TextOrPlain)
            map["mphone"] = mphone.toRequestBody(TextOrPlain)
            map["jdlbId"] = jdlbId.toRequestBody(TextOrPlain)
            map["zyzsyxq"] = zyzsyxq.toRequestBody(TextOrPlain)

            val part1 = MultipartBody.Part.createFormData(
                "fidzyzs_new",
                "",
                File(fidzyzs_new).asRequestBody("image/*".toMediaType())
            )
            val part2 = MultipartBody.Part.createFormData(
                "fidzczs_new",
                "",
                File(fidzczs_new).asRequestBody("image/*".toMediaType())
            )
            val mask = DialogHelper.showMask(this@JdryMyAddPictureAct)
            v1Api.createJdry(map, part1, part2)
                .observeOnMain(this@JdryMyAddPictureAct)
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

        titleBar.setOperation("保存").safeClicks().subscribe {
            if (param.fidzyzs_new.isBlank()) {
                ToastUtils.showShort("选择执业证书")
                return@subscribe
            }
            if (param.fidzczs_new.isBlank()) {
                ToastUtils.showShort("选择职称证书")
                return@subscribe
            }
            createJdry()
        }
    }

    private val param by lazy { intent.getSerializableExtra(Param) as JdryMyAddParam }

    override val layoutId = R.layout.act_jdry_my_add_picture

}