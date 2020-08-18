package com.unicorn.forensic2.refactor.caseDemo

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.forensic2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_case_demo.*
import org.ocpsoft.prettytime.PrettyTime

class CaseDemoHeader(context: Context, private val caseDemo: CaseDemo) : FrameLayout(context),
    LayoutContainer {

    override val containerView = this

    private val prettyTime = PrettyTime()

    init {
        LayoutInflater.from(context).inflate(R.layout.item_case_demo, this, true)
        with(caseDemo) {
            tvAddUser.text = addUser
            tvAddDttm.text = "发布于 ${prettyTime.format(java.util.Date(addDttm))}"
            tvContent.text = content
        }


    }

}