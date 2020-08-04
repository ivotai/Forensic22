package com.unicorn.forensic2.ui.operation

import android.view.View
import android.widget.RadioButton
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_hf.*

class HfAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("回复")
    }

    override fun bindIntent() {
        rgIsAccept.checkedChanges().subscribe {
            val radioButton = rgIsAccept.findViewById<RadioButton>(it)
            val flag = radioButton.text == "接受"
            etRejectInfo.visibility = if (flag) View.VISIBLE else View.GONE
        }

        titleBar.setOperation("确定").safeClicks().subscribe {

        }
    }

    private fun reply(){

    }

    override val layoutId = R.layout.act_hf

}