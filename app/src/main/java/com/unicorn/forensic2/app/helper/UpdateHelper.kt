package com.unicorn.forensic2.app.helper

import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.AppUtils
import com.kaopiz.kprogresshud.KProgressHUD
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.originalBaseUrl
import com.unicorn.forensic2.app.toast
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import io.reactivex.rxkotlin.subscribeBy
import okhttp3.Call
import java.io.File

object UpdateHelper {

    fun checkVersion(activity: AppCompatActivity) {
        val api = ComponentHolder.appComponent.v1Api()
        api.checkUpdate(AppUtils.getAppVersionName())
            .observeOnMain(activity)
            .subscribeBy(
                onNext = {
                    if (it.needUpdate) {
                        val apkUrl = "$originalBaseUrl${it.apkUrl}"
                        download(
                            activity = activity,
                            apkUrl = apkUrl
                        )
                    }
                },
                onError = {
                    it.message?.toast()
                }
            )
    }

    private fun download(activity: AppCompatActivity, apkUrl: String) {
        val mask = KProgressHUD.create(activity)
            .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
            .setCancellable(true)
            .setDimAmount(0.5f)
            .setMaxProgress(100)
            .show()
        OkHttpUtils
            .get()
            .url(apkUrl)
//            .addHeader(Cookie, "$SESSION=$session")
            .build()
            .execute(object : FileCallBack(
                activity.cacheDir.path,
                "Forensic.apk"
            ) {
                override fun onResponse(response: File, id: Int) {
                    mask.dismiss()
                    AppUtils.installApp(response)
                }

                override fun inProgress(progress: Float, total: Long, id: Int) {
                    val p = (100 * progress).toInt()
                    mask.setProgress(p)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    mask.dismiss()
                }
            })
    }

}