package com.unicorn.forensic2.ui.act.addOrEdit

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdry_my_add_or_edit_picture.*
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class JdjgMyEditPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传鉴定人员照片")
//        PictureHelper.load(this,"$pictureBaseUrl${jdryMyEditParam.fidzyzs}").into(ivFidzyzsNew)
//        PictureHelper.load(this,"$pictureBaseUrl${jdryMyEditParam.fidzczs}").into(ivFidzczsNew)
    }

    override fun bindIntent() {
//        ivFidzyzsNew.safeClicks().subscribe {
//            PictureHelper.selectPicture(
//                this@JdjgMyEditPictureAct,
//                object : OnResultCallbackListener {
//                    override fun onResult(result: MutableList<LocalMedia>) {
//                        val realPath = result[0].realPath
//                        jdryMyEditParam.fidzyzs_new = realPath
//                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidzyzsNew)
//                    }
//
//                    override fun onCancel() {
//                    }
//                })
//        }
//        ivFidzczsNew.safeClicks().subscribe {
//            PictureHelper.selectPicture(
//                this@JdjgMyEditPictureAct,
//                object : OnResultCallbackListener {
//                    override fun onResult(result: MutableList<LocalMedia>) {
//                        val realPath = result[0].realPath
//                        jdryMyEditParam.fidzczs_new = realPath
//                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidzczsNew)
//                    }
//
//                    override fun onCancel() {
//                    }
//                })
//        }

        fun save() = with(jdjg) {
            val map = HashMap<String, RequestBody>()
            map["jgmc"] = jgmc.toRequestBody(TextOrPlain)
            map["jgmc2"] = jgmc2.toRequestBody(TextOrPlain)
            map["jgszdId"] = jgszdId.toRequestBody(TextOrPlain)
            map["jgxzId"] = jgxzId.toRequestBody(TextOrPlain)
            map["zzlbId"] = zzlbId.toRequestBody(TextOrPlain)
            map["yyzzh"] = yyzzh.toRequestBody(TextOrPlain)
            map["xkzh"] = xkzh.toRequestBody(TextOrPlain)
            map["zczj"] = zczj.toRequestBody(TextOrPlain)
            map["fddb"] = fddb.toRequestBody(TextOrPlain)
            map["fddbSfzh"] = fddbSfzh.toRequestBody(TextOrPlain)
            map["clsj"] = clsj.toDisplayFormat().toRequestBody(TextOrPlain)
            map["bankname"] = bankname.toRequestBody(TextOrPlain)
            map["bankaccount"] = bankaccount.toRequestBody(TextOrPlain)
            map["yb"] = yb.toRequestBody(TextOrPlain)
            map["fzr"] = fzr.toRequestBody(TextOrPlain)
            map["fzrDh"] = fzrDh.toRequestBody(TextOrPlain)
            map["fzrSj"] = fzrSj.toRequestBody(TextOrPlain)
            map["lxr"] = lxr.toRequestBody(TextOrPlain)
            map["lxrCz"] = lxrCz.toRequestBody(TextOrPlain)
            map["lxrDh"] = lxrDh.toRequestBody(TextOrPlain)
            map["lxrSj"] = lxrSj.toRequestBody(TextOrPlain)
            map["lxrYx"] = lxrYx.toRequestBody(TextOrPlain)
            map["bgdz"] = bgdz.toRequestBody(TextOrPlain)
            map["zwpj"] = zwpj.toRequestBody(TextOrPlain)

//            var partZyzs:MultipartBody.Part?=null
//            var partZczs:MultipartBody.Part?=null
//            if (fidzyzs_new.isNotBlank()) {
//                partZyzs = MultipartBody.Part.createFormData(
//                    "fidzyzs_new",
//                    "",
//                    File(fidzyzs_new).asRequestBody("image/*".toMediaType())
//                )
//            }
//            if (fidzczs_new.isNotBlank()) {
//                partZczs = MultipartBody.Part.createFormData(
//                    "fidzczs_new",
//                    "",
//                    File(fidzczs_new).asRequestBody("image/*".toMediaType())
//                )
//            }
            val mask = DialogHelper.showMask(this@JdjgMyEditPictureAct)
            v1Api.editJdjgMy(jdjg.jgid, map)
                .observeOnMain(this@JdjgMyEditPictureAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("保存机构基本信息失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("保存机构基本信息成功")
                        finish()
                        RxBus.post(RefreshEvent())
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("保存机构基本信息失败")
                    }
                )
        }

        titleBar.setOperation("保存").safeClicks().subscribe { save() }
    }

    private val jdjg by lazy { intent.getSerializableExtra(Param) as Jdjg }

    override val layoutId = R.layout.act_jdry_my_add_or_edit_picture

}