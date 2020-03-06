package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Jdjg
import com.unicorn.forensic2.app.defaultPadding
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.adapter.JgzzAdapter
import com.unicorn.forensic2.ui.base.BaseAct
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import kotlinx.android.synthetic.main.ui_title_recycler.*

class JgzzListAct:BaseAct(){

    override fun initViews() {
        titleBar.setTitle("资质信息")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            simpleAdapter.bindToRecyclerView(this)
            addItemDecoration(LinearSpanDecoration(defaultPadding))
        }
    }

    override fun bindIntent() {
        simpleAdapter.setNewData(jdjg.jgzzList)
    }

    private val jdjg by lazy { intent.getSerializableExtra(Jdjg) as Jdjg}

    private val simpleAdapter = JgzzAdapter()

    override val layoutId: Int= R.layout.ui_title_recycler
}