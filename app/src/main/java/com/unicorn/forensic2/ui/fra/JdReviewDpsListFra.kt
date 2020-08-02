package com.unicorn.forensic2.ui.fra

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Psxx
import com.unicorn.forensic2.ui.adapter.PsxxAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single

class JdReviewDpsListFra:SimplePageFra<Psxx,KVHolder>(){

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }
    override val simpleAdapter: BaseQuickAdapter<Psxx, KVHolder>
            = PsxxAdapter()

    override fun loadPage(page: Int): Single<Page<Psxx>> = api.getJdReviewDpsList(page = page)

}