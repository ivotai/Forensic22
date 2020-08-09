package com.unicorn.forensic2.refactor.profile

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.ui.base.BaseFra
import com.unicorn.forensic2.ui.my.Backlog
import com.unicorn.forensic2.ui.my.BacklogAdapter
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fra_profile.*

class ProfileFra : BaseFra() {

    override fun initViews() {

        constraintLayout1.background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(blue600, blue300)
        )

        tvUsername.text = user.username
        rtvRealName.text = if (user.realName) "已认证" else "未认证"

        //
        recyclerView2.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            backlogAdapter.bindToRecyclerView(this)
            VerticalDividerItemDecoration.Builder(context)
                .colorResId(R.color.md_grey_300)
                .margin(ConvertUtils.dp2px(32f))
                .size(1)
                .build().let { this.addItemDecoration(it) }
        }

        //
        recyclerView3.apply {
            layoutManager = LinearLayoutManager(context)
            profileOperationAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.md_grey_200)
                .margin(ConvertUtils.dp2px(20f))
                .size(1)
                .build().let { this.addItemDecoration(it) }
        }
    }

    private val backlogAdapter = BacklogAdapter()
    private val profileOperationAdapter = ProfileOperationAdapter()

    override fun bindIntent() {
        profileOperationAdapter.setNewData(ProfileOperation.all)
        backlog()
    }

    private fun backlog() {
        api.backlog(roleTag = roleTag).observeOnMain(this).subscribeBy(
            onSuccess = { map ->
                val list = ArrayList<Backlog>()
                map.forEach {
                    val backlog = Backlog.findByKey(it.key)
                    backlog.count = it.value.toInt()
                    list.add(backlog)
                }
                backlogAdapter.setNewData(list)
            },
            onError = {}
        )
    }

    private val blue300 by lazy { ContextCompat.getColor(context!!, R.color.md_blue_300) }
    private val blue600 by lazy { ContextCompat.getColor(context!!, R.color.md_blue_600) }

    override val layoutId = R.layout.fra_profile

}