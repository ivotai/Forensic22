package com.unicorn.forensic2.ui.fra

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.act.query.CaseQueryAct
import com.unicorn.forensic2.ui.adapter.CaseTypeAdapter
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_case_main.*

class CaseMainFra : BaseFra() {

    private val caseTypeAdapter = CaseTypeAdapter()

    override fun initViews() {
        titleBar.setTitle("案件", false)
        rvCaseType.apply {
            layoutManager = LinearLayoutManager(context)
            caseTypeAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    override fun bindIntent() {
        caseTypeAdapter.setNewData(CaseType.all)

        titleBar.setOperation("查询").safeClicks()
            .subscribe { context!!.startAct(CaseQueryAct::class.java) }
    }

    override val layoutId = R.layout.fra_case_main

}