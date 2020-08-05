package com.unicorn.forensic2.ui.operation.tztx

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import io.reactivex.Single

class OldTztxListFra : SimplePageFra<Tztx, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Tztx, KVHolder> = TztxAdapter()

    override fun loadPage(page: Int): Single<Page<Tztx>> = api.notificationOld(page = page)

}