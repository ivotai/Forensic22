package com.unicorn.forensic2.refactor.tztx

import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Page
import com.unicorn.forensic2.ui.base.KVHolder
import com.unicorn.forensic2.ui.base.SimplePageFra
import com.unicorn.forensic2.ui.other.decoration.LinearSpanDecoration
import io.reactivex.Single

class NewTztxListFra : SimplePageFra<Tztx, KVHolder>() {

    override fun initViews() {
        super.initViews()
        mRecyclerView.background = ColorDrawable(ContextCompat.getColor(context!!, R.color.md_grey_200))
        mRecyclerView.addItemDecoration(LinearSpanDecoration(ConvertUtils.dp2px(16f)))
    }

    override val simpleAdapter: BaseQuickAdapter<Tztx, KVHolder> = TztxAdapter()

    override fun loadPage(page: Int): Single<Page<Tztx>> = api.notificationNew(page = page)

}