package com.unicorn.forensic2.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.UserMenu
import com.unicorn.forensic2.ui.adapter.UserMenuAdapter
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_home.*

class HomeFra : BaseFra() {

    override val layoutId = R.layout.fra_home

    override fun initViews() {
        card.background = GradientDrawable().apply {
            val dp = ConvertUtils.dp2px(20f).toFloat()
            cornerRadii = floatArrayOf(dp, dp, dp, dp, 0f, 0f, 0f, 0f)
            setColor(Color.WHITE)
        }

        tvUsername.text = if (isLogin) "欢迎您，$username" else "请登录"
        rtvIdentityChecked.visibility = if (isLogin) View.VISIBLE else View.INVISIBLE
        if (isLogin)
            rtvIdentityChecked.text = if (identityChecked) "已认证" else "未认证"

        fun initRv() {
            recyclerView.apply {
                layoutManager = GridLayoutManager(context!!, 3)
                userMenuAdapter.bindToRecyclerView(this)
            }
        }
        initRv()
    }

    private val userMenuAdapter = UserMenuAdapter()

    override fun bindIntent() {
        tvUsername.safeClicks().subscribe {
            if (!isLogin) activity?.startAct(LoginAct::class.java)
            else {
                isLogin = false
                RxBus.post(LoginStateChangeEvent())
            }
        }

        userMenuAdapter.setNewData(ArrayList<UserMenu>().apply { addAll(UserMenu.basicMenus) })
        if (isLogin) userMenuAdapter.addData(userMenu)
    }

}