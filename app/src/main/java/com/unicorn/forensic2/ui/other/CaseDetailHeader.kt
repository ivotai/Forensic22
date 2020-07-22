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
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.baseUrl
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.UploadFile
import com.unicorn.forensic2.ui.act.PdfAct
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
                val intent = Intent(context, PdfAct::class.java)
                intent.putExtra(Param, case.fidzbtz)
                context.startActivity(intent)
            }
        }
    }

}