package com.unicorn.forensic2.refactor.home

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.ui.act.LoginAct
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.fra_home.*

class HomeFra : BaseFra() {

    override fun initViews() {
        tvUsername.text = if (isLogin) "欢迎您，${user.username}" else "请登录"
        rtvIdentity.visibility = if (isLogin) View.VISIBLE else View.GONE

        fun initRvHomeMenu() {
            recyclerView.apply {
                layoutManager = GridLayoutManager(context!!, 3)
                simpleAdapter.bindToRecyclerView(this)
            }
        }
        initRvHomeMenu()
    }

    private val simpleAdapter = HomeOperationAdapter()

    override fun bindIntent() {
        simpleAdapter.setNewData(HomeOperation.all)

        tvUsername.safeClicks().subscribe { if (!isLogin) activity?.startAct(LoginAct::class.java) }
    }

    override val layoutId = R.layout.fra_home

}