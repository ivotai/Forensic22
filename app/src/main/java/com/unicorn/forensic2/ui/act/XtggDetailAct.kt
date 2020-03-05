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
        webView.loadData(xtgg.html, null, null)
    }

    override val layoutId = R.layout.ui_title_webview

}