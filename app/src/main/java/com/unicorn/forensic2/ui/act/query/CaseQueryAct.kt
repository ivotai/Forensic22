package com.unicorn.forensic2.ui.act.query

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.CaseQueryMapEvent
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_case_query.*

class CaseQueryAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("搜索")
    }

    override fun bindIntent() {
        tvLarqBegin.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@CaseQueryAct.tvLarqBegin.text = date.time.time.toDisplayFormat()
                }
            }
        }
        tvLarqEnd.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@CaseQueryAct.tvLarqEnd.text = date.time.time.toDisplayFormat()
                }
            }
        }
        fun showAjlxDialog() {
            MaterialDialog(this@CaseQueryAct).show {
                val items = ajlxList
                listItems(items = items.map { it.name }) { _, index, _ ->
                    flag = items[index].id
                    this@CaseQueryAct.tvFlag.text = items[index].name
                }
            }
        }
        tvFlag.safeClicks().subscribe { showAjlxDialog() }

        titleBar.setOperation("确认").safeClicks().subscribe {
            val queryMap = HashMap<String, Any>()
            if (tvLarqBegin.isNotBlack())
                queryMap["larqBegin"] = tvLarqBegin.trimText()
            if (tvLarqEnd.isNotBlack())
                queryMap["larqEnd"] = tvLarqEnd.trimText()
            if (flag.isNotBlank())
                queryMap["flag"] = flag
            RxBus.post(CaseQueryMapEvent(queryMap = queryMap))
            finish()
        }
    }

    private var flag = ""

    override val layoutId = R.layout.act_case_query

}