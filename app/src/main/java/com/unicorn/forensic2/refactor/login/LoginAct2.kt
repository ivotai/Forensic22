package com.unicorn.forensic2.refactor.login

import com.blankj.utilcode.util.ColorUtils
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_login2.*

class LoginAct2 : BaseAct() {

    override fun initViews() {
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

    override fun bindIntent() {
        tvUserLogin.clicks().subscribe {
            viewPager2.setCurrentItem(0,false)
            tvUserLogin.setTextColor(checkedColor)
            vUserLogin.setBackgroundColor(checkedColor)
            tvCourtLogin.setTextColor(unCheckedColor)
            vCourtLogin.setBackgroundColor(unCheckedColor)
        }
        tvCourtLogin.clicks().subscribe {
            viewPager2.setCurrentItem(1,false)
            tvCourtLogin.setTextColor(checkedColor)
            vCourtLogin.setBackgroundColor(checkedColor)
            tvUserLogin.setTextColor(unCheckedColor)
            vUserLogin.setBackgroundColor(unCheckedColor)
        }
    }

    override val layoutId = R.layout.act_login2

    private val unCheckedColor = ColorUtils.getColor(R.color.md_blue_300)
    private val checkedColor = ColorUtils.getColor(R.color.white)

}