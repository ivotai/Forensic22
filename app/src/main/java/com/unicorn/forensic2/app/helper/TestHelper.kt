package com.unicorn.forensic2.app.helper

import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.kaopiz.kprogresshud.KProgressHUD
import com.unicorn.forensic2.ui.other.FileUtils2
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import okhttp3.Call
import java.io.File

object TestHelper {

    fun check(context:Context){
        val url = "http://113.142.60.13:8088/apkFile/1.1_app-release.apk"
        val progressMask = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
            .setCancellable(true)
            .setDimAmount(0.5f)
            .setMaxProgress(100)
            .show()
//        val pdfId = uploadFile.fileid
//        val pdfName = uploadFile.filename
        OkHttpUtils
            .get()
            .url(url)
            .build()
            .execute(object : FileCallBack(context.cacheDir.path, "1.apk") {
                override fun onResponse(response: File, id: Int) {
                    progressMask.dismiss()
                    FileUtils2.openFile(context,response)
//                    FileOpen.openFile(context,response)
                }

                override fun inProgress(progress: Float, total: Long, id: Int) {
                    val p = (100 * progress).toInt()
                    progressMask.setProgress(p)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    progressMask.dismiss()
                    ToastUtils.showShort(e?.message)
                }
            })
    }

}