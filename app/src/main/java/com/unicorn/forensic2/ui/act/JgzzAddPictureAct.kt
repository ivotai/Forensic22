package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.JgzzAddParam
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.param.JgzzAddParam
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.other.GlideEngine
import kotlinx.android.synthetic.main.act_jgzz_add_picture.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class JgzzAddPictureAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("上传资质详情照片")
    }

    override fun bindIntent() {
        fun s(){
            PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .forResult(object :OnResultCallbackListener{
                    override fun onResult(result: MutableList<LocalMedia>) {
                        val path = result[0].path
                        param.fidzzzs_new = path
                        Glide.with(this@JgzzAddPictureAct).load(path).into(ivFidzzzsNew)
                    }

                    override fun onCancel() {
                    }
                })

        }
        ivFidzzzsNew.safeClicks().subscribe { s() }

        fun save()= with(param){
            val map = HashMap<String,RequestBody>()
            map["jdlbId"] = jdlbId.toString().toRequestBody("text/plain".toMediaType())
            map["zzdjId"] = zzdjId.toString().toRequestBody("text/plain".toMediaType())
            map["zzdjId"] = cylyId.toString().toRequestBody("text/plain".toMediaType())
            map["spjg"] = spjg.toRequestBody("text/plain".toMediaType())
            map["zzsm"] = zzsm.toRequestBody("text/plain".toMediaType())
            map["zzzh"] = zzzh.toRequestBody("text/plain".toMediaType())
            map["fidzzzs_new"] = File(fidzzzs_new).asRequestBody("image/*".toMediaType())
            v1Api.createJgzz(params = map)
                .observeOnMain(this@JgzzAddPictureAct)
                .subscribe (

                )
        }
        titleBar.setOperation("保存").safeClicks().subscribe {
            if (param.fidzzzs_new.isBlank()){
                ToastUtils.showShort("选择资质证书照片")
                return@subscribe
            }
            save()
        }
    }

    private val param by lazy { intent.getSerializableExtra(JgzzAddParam) as JgzzAddParam }

    override val layoutId = R.layout.act_jgzz_add_picture

}