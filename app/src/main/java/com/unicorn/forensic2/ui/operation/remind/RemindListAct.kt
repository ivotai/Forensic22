package com.unicorn.forensic2.ui.operation.remind

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_recycler.*

class RemindListAct : SimplePageAct<Remind, KVHolder>() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("办案提醒")
        mRecyclerView.addDefaultItemDecoration(1)
    }

    override val simpleAdapter: BaseQuickAdapter<Remind, KVHolder> = RemindAdapter()

    override fun loadPage(page: Int): Single<Page<Remind>> =
        v1Api.jdRemind(page = page, caseId = case.caseId)

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}