package com.unicorn.forensic2.ui.act.addOrEdit

import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.data.model.Expert
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_expert_edit.*

class ExpertEditAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("基本信息")
    }

    override fun bindIntent() {
        fun display() = with(expert) {
            etExpertName.setText(expertName)
            etSfzh.setText(sfzh)
            etPhoneNumber.setText(phoneNumber)
            etEmail.setText(email)
            etAddr.setText(addr)
            etZyms.setText(zyms)
        }
        display()

        etExpertName.textChanges().map { it.toString() }.subscribe { expert.expertName = it }
        etSfzh.textChanges().map { it.toString() }.subscribe { expert.sfzh = it }
        etPhoneNumber.textChanges().map { it.toString() }.subscribe { expert.phoneNumber = it }
        etEmail.textChanges().map { it.toString() }.subscribe { expert.email = it }
        etAddr.textChanges().map { it.toString() }.subscribe { expert.addr = it }
        etZyms.textChanges().map { it.toString() }.subscribe { expert.zyms = it }
    }

    private val expert by lazy { intent.getSerializableExtra(Param) as Expert }

    override val layoutId = R.layout.act_expert_edit

}