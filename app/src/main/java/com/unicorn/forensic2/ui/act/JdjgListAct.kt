package com.unicorn.forensic2.ui.act

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.adapter.JdjgAdapter
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageAct
import io.reactivex.Single
import kotlinx.android.synthetic.main.ui_title_swipe_recycler.*

class JdjgListAct : SimplePageAct<Jdjg, KVHolder>() {

    override fun initViews() {
        super.initViews()
//        recyclerView.addItemDecoration(LinearSpanDecoration(Config.defaultPaddingDp))
        titleBar.setTitle("机构列表")
    }

    override val simpleAdapter: BaseQuickAdapter<Jdjg, KVHolder> = JdjgAdapter()

    override fun loadPage(page: Int): Single<Page<Jdjg>> = v1Api.getJdjgList(page = page)

    override val mRecyclerView: RecyclerView get() = recyclerView

    override val mSwipeRefreshLayout: SwipeRefreshLayout get() = swipeRefreshLayout

    override val layoutId = R.layout.ui_title_swipe_recycler

}
