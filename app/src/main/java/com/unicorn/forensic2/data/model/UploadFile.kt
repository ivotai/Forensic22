package com.unicorn.forensic2.data.model

import android.content.Context
import android.content.Intent
import android.os.Environment
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.kaopiz.kprogresshud.KProgressHUD
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.baseUrl
import com.unicorn.forensic2.data.SimplePlayer
import com.unicorn.forensic2.ui.act.PdfAct
import com.unicorn.forensic2.ui.other.FileUtils2
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import okhttp3.Call
import java.io.File
import java.io.Serializable

data class UploadFile(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
) : Serializable {

    private val extension get() = FileUtils.getFileExtension(filename)

    fun open(context: Context) {
        when (extension) {
            "pdf" -> {
                val intent = Intent(context, PdfAct::class.java)
                intent.putExtra(Param, this)
                context.startActivity(intent)
            }
            "mp4" -> {
                val intent = Intent(context, SimplePlayer::class.java)
                intent.putExtra(Param, fileid)
                context.startActivity(intent)
            }
            else -> download(context)
//            else -> ToastUtils.showShort("手机端不支持${extension}格式文件查阅")
        }
    }

    fun download(context: Context){
        val progressMask = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
            .setCancellable(true)
            .setDimAmount(0.5f)
            .setMaxProgress(100)
            .show()
//        val pdfId = uploadFile.fileid
//        val pdfName = uploadFile.filename
        val pdfUrl = "${baseUrl}sysFileinfo/download/${fileid}"
        OkHttpUtils
            .get()
            .url(pdfUrl)
            .build()
            .execute(object : FileCallBack(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path, "1.$extension") {
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