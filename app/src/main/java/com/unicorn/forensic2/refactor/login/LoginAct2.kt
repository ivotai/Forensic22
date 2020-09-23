package com.unicorn.forensic2.refactor.login

import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_login2.*

class LoginAct2 : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("登录")
        initViewPager2()
    }

    private fun initViewPager2(){
        viewPager2.run {
            isUserInputEnabled = false
            offscreenPageLimit = LoginFragmentStateAdapter.titles.size - 1
            adapter = LoginFragmentStateAdapter(this@LoginAct2)
        }

//        v1Api.getFy().observeOnMain(this).subscribe()
    }

    override val layoutId = R.layout.act_login2

}