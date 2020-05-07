package com.unicorn.forensic2.ui.act.query

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.TreeResult2
import com.unicorn.forensic2.ui.act.FyTreeAct
import com.unicorn.forensic2.ui.act.tree.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_roll_query.*

class RollQueryAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("搜索")
    }

    override fun bindIntent() {
        tvJdfy.safeClicks().subscribe { startAct(FyTreeAct::class.java) }

        tvBeginDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@RollQueryAct.tvBeginDate.text = date.time.time.toDisplayFormat()
                }
            }
        }
        tvEndDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@RollQueryAct.tvEndDate.text = date.time.time.toDisplayFormat()
                }
            }
        }
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }

        titleBar.setOperation("确认").safeClicks().subscribe {
            val queryMap = HashMap<String, Any>()
            if (courtCode.isNotBlank())
                queryMap["courtCode"] = courtCode
            if (tvBeginDate.isNotBlack())
                queryMap["beginDate"] = tvBeginDate.trimText()
            if (tvEndDate.isNotBlack())
                queryMap["endDate"] = tvBeginDate.trimText()
            if (jdlbId.isNotBlank())
                queryMap["jdlb"] = jdlbId
            if (etCaseNo.isNotBlack())
                queryMap["caseNo"] = etCaseNo.trimText()
            RxBus.post(QueryMapEvent(queryMap = queryMap))
            finish()
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            jdlbId = it.dict.id
            tvJdlb.text = it.dict.name
        })
        RxBus.registerEvent(this, TreeResult2::class.java, Consumer {
            courtCode = it.fy.dm
            tvJdfy.text = it.fy.dmms
        })
    }

    private var courtCode = ""

    private var jdlbId = ""

    override val layoutId = R.layout.act_roll_query

}