package com.unicorn.forensic2.data.model

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.SimplePlayer
import com.unicorn.forensic2.ui.act.PdfAct
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
                intent.putExtra(Param, fileid)
                context.startActivity(intent)
            }
            "mp4" -> {
                val intent = Intent(context, SimplePlayer::class.java)
                intent.putExtra(Param, fileid)
                context.startActivity(intent)
            }
            else -> ToastUtils.showShort("手机端不支持该格式文件查阅")
        }
    }

}