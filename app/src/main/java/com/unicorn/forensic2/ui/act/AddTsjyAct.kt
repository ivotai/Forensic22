package com.unicorn.forensic2.ui.act

import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.unicorn.forensic2.R
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_add_tsjy.*

class AddTsjyAct:BaseAct(){

    override fun initViews() {
        titleBar.setTitle("提交投诉建议")

        GradientDrawable().apply {
            setStroke(1,ContextCompat.getColor(this@AddTsjyAct,R.color.md_black))
        }.let { etContent.background = it }
    }

    override fun bindIntent() {

    }

    override val layoutId = R.layout.act_add_tsjy

}