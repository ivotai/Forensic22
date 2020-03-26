package com.unicorn.forensic2.ui.fra

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.model.MyMenu
import com.unicorn.forensic2.ui.adapter.MyMenuAdapter
import com.unicorn.forensic2.ui.base.BaseFra
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
    }

    override fun bindIntent() {
        simpleAdapter.setNewData(MyMenu.all)
        getHomeInfo()
    }

    private fun getHomeInfo() {
        api.getHomeInfo().observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    with(it) {
                        if (user.JdjgAdmin) {
                            tvLabel1.text = "中标通知"
                            tvNum1.text = zbtz
                            tvLabel2.text = "待鉴定"
                            tvNum2.text = djd
                            tvLabel3.visibility = View.INVISIBLE
                            tvNum3.visibility = View.INVISIBLE
                        } else if (user.Admin) {
                            tvLabel1.text = "机构注册"
                            tvNum1.text = jgzc
                            tvLabel2.text = "机构变更"
                            tvNum2.text = jgbg
                            tvLabel3.text = "专家注册"
                            tvNum3.text = zjzc
                        } else if (user.Pszj) {
                            tvLabel1.text = "待评审"
                            tvNum1.text = dps
                            tvLabel2.visibility = View.INVISIBLE
                            tvNum2.visibility = View.INVISIBLE
                            tvLabel3.visibility = View.INVISIBLE
                            tvNum3.visibility = View.INVISIBLE
                        }
                    }
                },
                onError = {
                }
            )
    }

    private val colorPrimary by lazy { ContextCompat.getColor(context!!, R.color.colorPrimary) }
    private val blue300 by lazy { ContextCompat.getColor(context!!, R.color.md_blue_300) }

    override val layoutId = R.layout.fra_main_my

}