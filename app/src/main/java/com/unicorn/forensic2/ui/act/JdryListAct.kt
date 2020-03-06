package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Jdjg
import com.unicorn.forensic2.app.defaultPadding
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.adapter.JdryAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import kotlinx.android.synthetic.main.ui_title_recycler.*

class JdryListAct:BaseAct(){

    override fun initViews() {
        titleBar.setTitle("鉴定人员")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            addItemDecoration(LinearSpanDecoration(defaultPadding))
        }
    }

    override fun bindIntent() {
        simpleAdapter.setNewData(jdjg.jdryList)
    }

    private val jdjg by lazy { intent.getSerializableExtra(Jdjg) as Jdjg}

    private val simpleAdapter = JdryAdapter()

    override val layoutId: Int= R.layout.ui_title_recycler
}