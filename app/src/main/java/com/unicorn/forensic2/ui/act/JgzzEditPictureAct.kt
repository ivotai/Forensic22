package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.param.JgzzEditParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jgzz_add_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JgzzEditPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传资质详情照片")
    }

    override fun bindIntent() {
        ivFidzzzsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(this@JgzzEditPictureAct, object : OnResultCallbackListener {
                override fun onResult(result: MutableList<LocalMedia>) {
                    val realPath = result[0].realPath
                    jgzzEditParam.fidzzzs_new = realPath
                    Glide.with(this@JgzzEditPictureAct).load(realPath).into(ivFidzzzsNew)
                }

                override fun onCancel() {
                }
            })
        }

        fun saveJgzz() = with(jgzzEditParam) {
            val map = HashMap<String, RequestBody>()
            map["jdlbId"] = jdlbId.toString().toRequestBody(TextOrPlain)
            map["zzdjId"] = zzdjId.toString().toRequestBody(TextOrPlain)
            map["cylyId"] = cylyId.toString().toRequestBody(TextOrPlain)
            map["yxrq"] = yxrq.toRequestBody(TextOrPlain)
            map["spjg"] = spjg.toRequestBody(TextOrPlain)
            map["zzsm"] = zzsm.toRequestBody(TextOrPlain)
            map["zzzh"] = zzzh.toRequestBody(TextOrPlain)

            val file = File(fidzzzs_new)
            val part = MultipartBody.Part.createFormData(
                "fidzzzs_new",
                file.name,
                file.asRequestBody("image/*".toMediaType())
            );
            val mask = DialogHelper.showMask(this@JgzzEditPictureAct)
            v1Api.editJgzz(jgzzEditParam.objectId, map, part)
                .observeOnMain(this@JgzzEditPictureAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("保存机构资质失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("保存机构资质成功")
                        finish()
                        RxBus.post(RefreshEvent())
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("保存机构资质失败")
                    }
                )
        }

        titleBar.setOperation("保存").safeClicks().subscribe {
            if (jgzzEditParam.fidzzzs_new.isBlank()) {
                ToastUtils.showShort("选择资质证书照片")
                return@subscribe
            }
            saveJgzz()
        }
    }

    private val jgzzEditParam by lazy { intent.getSerializableExtra(Param) as JgzzEditParam }

    override val layoutId = R.layout.act_jgzz_add_picture

}