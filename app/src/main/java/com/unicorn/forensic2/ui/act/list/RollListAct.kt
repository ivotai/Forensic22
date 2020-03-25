package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Roll
import com.unicorn.forensic2.ui.adapter.RollAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class RollListAct : SimplePageAct<Roll, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("鉴定摇号")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Roll, KVHolder> = RollAdapter()

    override fun loadPage(page: Int): Single<Page<Roll>> = v1Api.getRollList(page = page)

}
