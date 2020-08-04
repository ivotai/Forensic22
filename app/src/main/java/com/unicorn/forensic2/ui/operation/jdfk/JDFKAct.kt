package com.unicorn.forensic2.ui.operation.jdfk

import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_jdfk.*

class JDFKAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle(Operation.JDFK.cn)
    }

    override val layoutId = R.layout.act_jdfk

}