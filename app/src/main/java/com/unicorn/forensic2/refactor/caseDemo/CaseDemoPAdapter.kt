package com.unicorn.forensic2.refactor.caseDemo

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_case_demo.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class CaseDemoPAdapter : BaseQuickAdapter<CaseDemo, KVHolder>(R.layout.item_case_demo) {

    private val prettyTime = PrettyTime()

    override fun convert(helper: KVHolder, item: CaseDemo) {
        helper.apply {
            tvAddUser.text = item.addUser
            tvAddDttm.text = "发布于 ${prettyTime.format(Date(item.addDttm))}"
            tvContent.text = item.content
        }
        helper.root.safeClicks().subscribe {
            Intent(mContext,CaseDemoCListAct::class.java).apply {
                putExtra(Param,item)
            }.let { mContext.startActivity(it) }
        }
    }

}