package com.unicorn.forensic2.ui.fra

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.model.HomeMenu
import com.unicorn.forensic2.ui.act.LoginAct
import com.unicorn.forensic2.ui.adapter.HomeMenuAdapter
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_home_main.*

class HomeMainFra : BaseFra() {

    override fun initViews() {
        titleBar.setTitle("司法鉴定", false)
        tvUsername.text = if (isLogin) "欢迎您，${user.username}" else "请登录"
        rtvIdentity.visibility = if (isLogin) View.VISIBLE else View.GONE

        fun initRvHomeMenu() {
            rvHomeMenu.apply {
                layoutManager = GridLayoutManager(context!!, 3)
                homeMenuAdapter.bindToRecyclerView(this)
            }
        }
        initRvHomeMenu()
    }

    private val homeMenuAdapter = HomeMenuAdapter()

    override fun bindIntent() {
        homeMenuAdapter.setNewData(HomeMenu.all)

        tvUsername.safeClicks().subscribe { if (!isLogin) activity?.startAct(LoginAct::class.java) }
    }

    override val layoutId = R.layout.fra_home_main

}