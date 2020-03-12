package com.unicorn.forensic2.ui.act

import com.bumptech.glide.Glide
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.JgzzAddParam
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.param.JgzzAddParam
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.other.GlideEngine
import kotlinx.android.synthetic.main.act_jgzz_add_picture.*

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
    }

    private val param by lazy { intent.getSerializableExtra(JgzzAddParam) as JgzzAddParam }

    override val layoutId = R.layout.act_jgzz_add_picture

}