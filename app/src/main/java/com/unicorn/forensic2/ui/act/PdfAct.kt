package com.unicorn.forensic2.ui.act

import com.kaopiz.kprogresshud.KProgressHUD
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.baseUrl
import com.unicorn.forensic2.ui.base.BaseAct
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import kotlinx.android.synthetic.main.act_pdf.*
import okhttp3.Call
import java.io.File

class PdfAct : BaseAct() {

    override fun bindIntent() {
        super.bindIntent()
        download()
    }

    private fun download() {
        val progressMask = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
            .setCancellable(true)
            .setDimAmount(0.5f)
            .setMaxProgress(100)
            .show()
//        val pdfId = uploadFile.fileid
//        val pdfName = uploadFile.filename
        val pdfUrl = "${baseUrl}sysFileinfo/download/$pdfId"
        OkHttpUtils
            .get()
            .url(pdfUrl)
            .build()
            .execute(object : FileCallBack(cacheDir.path, ".pdf") {
                override fun onResponse(response: File, id: Int) {
                    progressMask.dismiss()
                    openPdf(file = response)
                }

                override fun inProgress(progress: Float, total: Long, id: Int) {
                    val p = (100 * progress).toInt()
                    progressMask.setProgress(p)
                }

                override fun onError(call: Call?, e: Exception?, id: Int) {
                    progressMask.dismiss()
                }
            })
    }

    private fun openPdf(file: File) {
        pdfView.fromFile(file).load()
    }

    override val layoutId = R.layout.act_pdf

    private val pdfId by lazy { intent.getSerializableExtra(Param) as String }
}