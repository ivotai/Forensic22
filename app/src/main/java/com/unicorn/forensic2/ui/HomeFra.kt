package com.unicorn.forensic2.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.forensic2.R
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
    }

}