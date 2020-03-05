package com.unicorn.forensic2.ui.act

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.defaultPadding
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Xtgg
import com.unicorn.forensic2.ui.adapter.XtggAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class XtggListAct : SimplePageAct<Xtgg, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("系统公告")
        recyclerView.addItemDecoration(LinearSpanDecoration(defaultPadding))
    }

    override val simpleAdapter: BaseQuickAdapter<Xtgg, KVHolder> = XtggAdapter()

    override fun loadPage(page: Int): Single<Page<Xtgg>> = v1Api.getXtggList(page=page)

}