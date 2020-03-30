package com.unicorn.forensic2.ui.act.query

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_shjl_jdjg_query.*

class ShjlJdjgQueryAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("搜索")
    }

    override fun bindIntent() {
        tvCzrqFrom.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@ShjlJdjgQueryAct.tvCzrqFrom.text = date.time.time.toDisplayFormat()
                }
            }
        }
        tvCzrqTo.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    this@ShjlJdjgQueryAct.tvCzrqTo.text = date.time.time.toDisplayFormat()
                }
            }
        }
        fun showShjgDialog() {
            MaterialDialog(this@ShjlJdjgQueryAct).show {
                listItems(items = shjgList.map { it.name }) { _, index, _ ->
                    dataFlag = shjgList[index].id
                    this@ShjlJdjgQueryAct.tvDataFlag.text = shjgList[index].name
                }
            }
        }
        tvDataFlag.safeClicks().subscribe { showShjgDialog() }

        titleBar.setOperation("确认").safeClicks().subscribe {
            val queryMap = HashMap<String, Any>()
            if (tvCzrqFrom.isNotBlack())
                queryMap["CzrqFrom"] = tvCzrqFrom.trimText()
            if (tvCzrqTo.isNotBlack())
                queryMap["CzrqTo"] = tvCzrqTo.trimText()
            if (dataFlag.isNotBlank())
                queryMap["DataFlag"] = dataFlag
            RxBus.post(QueryMapEvent(queryMap = queryMap))
            finish()
        }
    }

    private var dataFlag = ""

    override val layoutId = R.layout.act_shjl_jdjg_query

}