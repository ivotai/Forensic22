package com.unicorn.forensic2.refactor.main.home

import androidx.recyclerview.widget.GridLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.refactor.login.LoginAct2
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_home.*

class HomeFra : BaseFra() {

    override fun initViews() {
        tvUsername.text = if (isLogin) "欢迎您，${user.username}" else "请登录"
        recyclerView.apply {
            layoutManager = GridLayoutManager(context!!, 3)
            simpleAdapter.bindToRecyclerView(this)
        }
    }

    private val simpleAdapter = HomeOperationAdapter()

    override fun bindIntent() {
        simpleAdapter.setNewData(HomeOperation.all)

        tvUsername.safeClicks().subscribe { if (!isLogin) activity?.startAct(LoginAct2::class.java) }
    }

    override val layoutId = R.layout.fra_home

}