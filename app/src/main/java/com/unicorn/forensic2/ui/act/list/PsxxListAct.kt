package com.unicorn.forensic2.ui.act.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Psxx
import com.unicorn.forensic2.ui.adapter.PsxxAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class PsxxListAct : SimplePageAct<Psxx, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("评审信息")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Psxx, KVHolder> = PsxxAdapter()

    override fun loadPage(page: Int): Single<Page<Psxx>> = v1Api.getPsxxList(page = page)

}
