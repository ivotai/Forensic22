package com.unicorn.forensic2.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.username
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

        tvUsername.text = if (isLogin) username else "请登录"
    }



    override fun bindIntent() {
        tvUsername.safeClicks().subscribe {
            if (!isLogin) activity?.startAct(LoginAct::class.java)
        }
    }

}