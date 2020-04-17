package com.unicorn.forensic2.app.helper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
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

    fun load(context: Context, url: String) =
        Glide.with(context)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)


    fun getPath(result: MutableList<LocalMedia>): String {
        var path = result[0].path
        if (path.startsWith("content")) path = result[0].realPath
        return path
    }

}