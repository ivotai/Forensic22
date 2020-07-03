package com.unicorn.forensic2.ui.other

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.kaopiz.kprogresshud.KProgressHUD
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.baseUrl
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.UploadFile
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_case_detail.view.*
import okhttp3.Call
import java.io.File


class CaseDetailHeader(context: Context, case: Case) : FrameLayout(context),
    LayoutContainer {

    override val containerView = this

    init {
        LayoutInflater.from(context).inflate(R.layout.header_case_detail, this, true)
        with(case) {
            tvJdNo.text = jdNo
            tvCaseNo.text = caseNo
            tvCaseStatus.text = caseStatus
            tvDateApply.text = dateApply.toDisplayFormat()
            tvPlanFinish.text = planFinish.toDisplayFormat()
            tvCloseTypeDisplay.text = closeTypeDisplay
            tvJdryxm.text = jdryxm
        }

        // 下载中标通知
        if (case.fidzbtz != null) {
            tvZbtz.text = case.fidzbtz.filename
            tvZbtz.safeClicks().subscribe {
                val pdf = File(context.cacheDir.path, case.fidzbtz.filename)
                if (pdf.exists()) {
                    openPdf(context,pdf)
                } else
                    download(context, case.fidzbtz)
            }
        }
    }

    private fun download(context: Context, uploadFile: UploadFile) {
        val progressMask = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.BAR_DETERMINATE)
            .setCancellable(true)
            .setDimAmount(0.5f)
            .setMaxProgress(100)
            .show()
        val pdfId = uploadFile.fileid
        val pdfName = uploadFile.filename
        val pdfUrl = "${baseUrl}sysFileinfo/download/$pdfId"
        OkHttpUtils
            .get()
            .url(pdfUrl)
            .build()
            .execute(object : FileCallBack(context.cacheDir.path, pdfName) {
                override fun onResponse(response: File, id: Int) {
                    progressMask.dismiss()
                    openPdf(context,file = response)
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

    private fun openPdf(context: Context,file: File) {
        val intent =  Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        val uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        context.startActivity(intent)
    }

}