package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Xtgg
import com.unicorn.forensic2.data.model.Xtgg
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.ui_title_webview.*

class XtggDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("公告详情")
    }

    override fun bindIntent() {
        val xtgg = intent.getSerializableExtra(Xtgg) as Xtgg
        webView.loadDataWithBaseURL(null, xtgg.html, "text/html", "utf-8", null);
//        webView.loadData(xtgg.html, "text/html; charset=UTF-8", null)
    }

    override val layoutId = R.layout.ui_title_webview

}