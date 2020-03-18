package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Shjl
import com.unicorn.forensic2.ui.adapter.ShjlAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class ShjlExpertListAct : SimplePageAct<Shjl, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("审核记录")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Shjl, KVHolder> = ShjlAdapter()

    override fun loadPage(page: Int): Single<Page<Shjl>> = v1Api.getShjlExpertList(page = page)

}
