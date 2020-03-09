package com.unicorn.forensic2.ui.act

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.data.model.Tsjy
import com.unicorn.forensic2.ui.adapter.TsjyWhfAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class TsjyWhfListAct : SimplePageAct<Tsjy, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("投诉建议")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Tsjy, KVHolder> = TsjyWhfAdapter()

    override fun loadPage(page: Int): Single<Page<Tsjy>> = v1Api.getTsjyWhfList(page)

}
