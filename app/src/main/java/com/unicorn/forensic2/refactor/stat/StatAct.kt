package com.unicorn.forensic2.refactor.stat

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.ui.base.BaseAct
import org.joda.time.DateTime

class StatAct : BaseAct() {

    val beginDate = DateTime()

    val endDate = beginDate

    override fun bindIntent() {
        v1Api.stat(
            beginDate = beginDate.toString(displayDateFormat),
            endDate = endDate.toString(displayDateFormat)
        )
            .observeOnMain(this)
            .subscribe()
    }

    override val layoutId = R.layout.act_stat

}