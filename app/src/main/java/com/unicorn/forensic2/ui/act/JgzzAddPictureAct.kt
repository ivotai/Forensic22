package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.helper.PictureHelper
import com.unicorn.forensic2.data.event.JgzzMyListNeedRefreshEvent
import com.unicorn.forensic2.data.model.param.JgzzAddParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jgzz_add_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JgzzAddPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传资质详情照片")
    }

    override fun bindIntent() {
        ivFidzzzsNew.safeClicks().subscribe {
            PictureHelper.selectPicture(this@JgzzAddPictureAct, object : OnResultCallbackListener {
                override fun onResult(result: MutableList<LocalMedia>) {
                    val realPath = result[0].realPath
                    param.fidzzzs_new = realPath
                    Glide.with(this@JgzzAddPictureAct).load(realPath).into(ivFidzzzsNew)
                }

                override fun onCancel() {
                }
            })
        }

        fun createJgzz() = with(param) {
            val map = HashMap<String, RequestBody>()
            map["jdlbId"] = jdlbId.toString().toRequestBody(TextOrPlainType)
            map["zzdjId"] = zzdjId.toString().toRequestBody(TextOrPlainType)
            map["cylyId"] = cylyId.toString().toRequestBody(TextOrPlainType)
            map["yxrq"] = yxrq.toRequestBody(TextOrPlainType)
            map["spjg"] = spjg.toRequestBody(TextOrPlainType)
            map["zzsm"] = zzsm.toRequestBody(TextOrPlainType)
            map["zzzh"] = zzzh.toRequestBody(TextOrPlainType)

            // 另一种方法，暂时不用，用 map 传递 file
            //注意：file就是与服务器对应的key,后面filename是服务器得到的文件名
//            map.put("file\"; filename=\"" + file.getName(), requestFile);
//            map["fidzzzs_new"] = File(fidzzzs_new).asRequestBody("image/jpeg".toMediaType())

            val file = File(fidzzzs_new)
            val part = MultipartBody.Part.createFormData(
                "fidzzzs_new",
                file.name,
                file.asRequestBody("image/*".toMediaType())
            );
            val mask = DialogHelper.showMask(this@JgzzAddPictureAct)
            v1Api.createJgzz(map, part)
                .observeOnMain(this@JgzzAddPictureAct)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        if (!it.success) {
                            ToastUtils.showShort("保存机构资质失败")
                            return@subscribeBy
                        }
                        ToastUtils.showShort("保存机构资质成功")
                        finish()
                        RxBus.post(JgzzMyListNeedRefreshEvent())
                    },
                    onError = {
                        mask.dismiss()
                        ToastUtils.showShort("保存机构资质失败")
                    }
                )
        }

        titleBar.setOperation("保存").safeClicks().subscribe {
            if (param.fidzzzs_new.isBlank()) {
                ToastUtils.showShort("选择资质证书照片")
                return@subscribe
            }
            createJgzz()
        }
    }

    private val param by lazy { intent.getSerializableExtra(JgzzAddParam) as JgzzAddParam }

    override val layoutId = R.layout.act_jgzz_add_picture

}