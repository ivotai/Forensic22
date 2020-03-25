package com.unicorn.forensic2.ui.fra

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.model.MyMenu
import com.unicorn.forensic2.ui.adapter.MyMenuAdapter
import com.unicorn.forensic2.ui.base.BaseFra
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
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

//    override fun onResume() {
//        fun getCurrentUserInfo() {
////            api.getCurrentUserInfo()
////                .observeOnMain(this)
////                .subscribe {
////                    Global.userInfo = it.data
////                    tvLoginName.text = Global.userInfo.loginName
////                }
//        }
//
//        getCurrentUserInfo()
//        super.onResume()
//    }


    override fun bindIntent() {
        simpleAdapter.setNewData(MyMenu.all)
    }

    private val colorPrimary by lazy { ContextCompat.getColor(context!!, R.color.colorPrimary) }
    private val blue300 by lazy { ContextCompat.getColor(context!!, R.color.md_blue_300) }

    override val layoutId = R.layout.fra_main_my

}