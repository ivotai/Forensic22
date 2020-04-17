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
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my_edit_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JdjgMyEditPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传机构信息照片")
        PictureHelper.load(this, "$pictureBaseUrl${jdjg.fidyyzz}").into(ivFidyyzzNew)
        PictureHelper.load(this, "$pictureBaseUrl${jdjg.fidxkzs}").into(ivFidxkzsNew)
        PictureHelper.load(this, "$pictureBaseUrl${jdjg.fidfrsfz}").into(ivFidfrsfzNew)
        PictureHelper.load(this, "$pictureBaseUrl${jdjg.fidbacl}").into(ivFidbaclNew)
    }

    override fun bindIntent() {
        ivFidyyzzNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdjgMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdjg.fidyyzz_new = realPath
                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidyyzzNew)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivFidxkzsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdjgMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdjg.fidxkzs_new = realPath
                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidxkzsNew)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivFidfrsfzNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdjgMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdjg.fidfrsfz_new = realPath
                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidfrsfzNew)
                    }

                    override fun onCancel() {
                    }
                })
        }
        ivFidbaclNew.safeClicks().subscribe {
            PictureHelper.selectPicture(
                this@JdjgMyEditPictureAct,
                object : OnResultCallbackListener {
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val realPath = result[0].realPath
                        jdjg.fidbacl_new = realPath
                        Glide.with(this@JdjgMyEditPictureAct).load(realPath).into(ivFidbaclNew)
                    }

                    override fun onCancel() {
                    }
                })
        }

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

            var partYyzz: MultipartBody.Part? = null
            var partXkzs: MultipartBody.Part? = null
            var partFrsfz: MultipartBody.Part? = null
            var partBacl: MultipartBody.Part? = null
            if (fidyyzz_new?.isNotBlank() == true) {
                partYyzz = MultipartBody.Part.createFormData(
                    "fidyyzz_new",
                    "",
                    File(fidyyzz_new).asRequestBody("image/*".toMediaType())
                )
            }
            if (fidxkzs_new?.isNotBlank() == true) {
                partXkzs = MultipartBody.Part.createFormData(
                    "fidxkzs_new",
                    "",
                    File(fidxkzs_new).asRequestBody("image/*".toMediaType())
                )
            }
            if (fidfrsfz_new?.isNotBlank() == true) {
                partFrsfz = MultipartBody.Part.createFormData(
                    "fidfrsfz_new",
                    "",
                    File(fidfrsfz_new).asRequestBody("image/*".toMediaType())
                )
            }
            if (fidbacl_new?.isNotBlank() == true) {
                partBacl = MultipartBody.Part.createFormData(
                    "fidbacl_new",
                    "",
                    File(fidbacl_new).asRequestBody("image/*".toMediaType())
                )
            }
            val mask = DialogHelper.showMask(this@JdjgMyEditPictureAct)
            v1Api.editJdjgMy(jdjg.jgid, map, partYyzz, partXkzs, partFrsfz, partBacl)
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

    override val layoutId = R.layout.act_jdjg_my_edit_picture

}