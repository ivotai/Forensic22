package com.unicorn.forensic2.ui.act.query

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_case_query.*

class CaseQueryAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("搜索")
    }

    override fun bindIntent() {
//        tvLarqBegin.safeClicks().subscribe {
//            MaterialDialog(this).show {
//                datePicker { _, date ->
//                    this@CaseQueryAct.tvLarqBegin.text = date.time.time.toDisplayFormat()
//                }
//            }
//        }
//        tvLarqEnd.safeClicks().subscribe {
//            MaterialDialog(this).show {
//                datePicker { _, date ->
//                    this@CaseQueryAct.tvLarqEnd.text = date.time.time.toDisplayFormat()
//                }
//            }
//        }
//        fun showAjlxDialog() {
//            MaterialDialog(this@CaseQueryAct).show {
//                val items = ajlxList
//                listItems(items = items.map { it.name }) { _, index, _ ->
//                    flag = items[index].id
//                    this@CaseQueryAct.tvFlag.text = items[index].name
//                }
//            }
//        }
//        tvFlag.safeClicks().subscribe { showAjlxDialog() }

        titleBar.setOperation("确认").safeClicks().subscribe {
            val queryMap = HashMap<String, Any>()
            queryMap["ah"] = tvAh.trimText()
            queryMap["year"]= tvYear.trimText()
            queryMap["plaintiff"]= tvPlaintiff.trimText()
//            if (tvLarqBegin.isNotBlack())
//                queryMap["larqBegin"] = tvLarqBegin.trimText()
//            if (flag.isNotBlank())
//                queryMap["flag"] = flag
            RxBus.post(QueryMapEvent(queryMap = queryMap))
            finish()
        }
    }

    override val layoutId = R.layout.act_case_query

}