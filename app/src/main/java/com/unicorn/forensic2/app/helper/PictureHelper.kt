package com.unicorn.forensic2.app.helper

import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.unicorn.forensic2.ui.other.GlideEngine

object PictureHelper {

    fun selectPicture(act: AppCompatActivity, listener: OnResultCallbackListener) {
        PictureSelector.create(act)
            .openGallery(PictureMimeType.ofImage())
            .loadImageEngine(GlideEngine.createGlideEngine())
            .maxSelectNum(1)
            .forResult(listener)
    }

}