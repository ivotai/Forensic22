package com.unicorn.forensic2.ui.fra

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.addDefaultItemDecoration
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.data.model.MyMenu
import com.unicorn.forensic2.ui.adapter.MyMenuAdapter
import com.unicorn.forensic2.ui.base.BaseFra
import com.unicorn.forensic2.ui.my.Backlog
import com.unicorn.forensic2.ui.my.BacklogAdapter
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fra_main_my.*

class MyMainFra : BaseFra() {

    private val simpleAdapter = MyMenuAdapter()

    override fun initViews() {

        fun initRecyclerView() {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                simpleAdapter.bindToRecyclerView(this)
                HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_300)
                    .margin(ConvertUtils.dp2px(16f), 0)
                    .size(1)
                    .build().let { this.addItemDecoration(it) }
            }
        }

        constraintLayout1.background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(colorPrimary, blue300)
        )

        initRecyclerView()
        tvUsername.text = user.username
        rtvRealName.text = if (user.realName) "已认证" else "未认证"

        recyclerView2.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            backlogAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration(1)
        }
    }

    private val backlogAdapter = BacklogAdapter()

    override fun bindIntent() {
        simpleAdapter.setNewData(MyMenu.all)
        backlog()
    }

    override fun onResume() {
        super.onResume()
        backlog()
    }

    private fun backlog() {
        api.backlog(roleTag = roleTag).observeOnMain(this).subscribeBy(
            onSuccess = { map ->
                val list = ArrayList<Backlog>()
                map.forEach {
                    val caseType = CaseType.findByKey(it.key)
                    list.add(Backlog(caseType = caseType, count = it.value.toInt()))
                }
                backlogAdapter.setNewData(list)
            },
            onError = {

            }
        )
    }

    private val colorPrimary by lazy { ContextCompat.getColor(context!!, R.color.colorPrimary) }
    private val blue300 by lazy { ContextCompat.getColor(context!!, R.color.md_blue_300) }

    override val layoutId = R.layout.fra_main_my

}