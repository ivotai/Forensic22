package com.unicorn.forensic2.refactor.stat

import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_stat.*
import org.joda.time.DateTime

class StatAct : BaseAct() {

    private var beginDate = DateTime().withDayOfMonth(1).toString(displayDateFormat)

    private var endDate = DateTime().toString(displayDateFormat)

    val simpleAdapter = StatAdapter()

    override fun initViews() {
        titleBar.setTitle("收结存统计")
        tvBeginDate.text = "开始日期 $beginDate"
        tvEndDate.text = "结束日期 $endDate"

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatAct)
            simpleAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        tvBeginDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    beginDate = date.time.time.toDisplayFormat()
                    this@StatAct.tvBeginDate.text = "开始日期 $beginDate"
                    getStat()
                }
            }
        }

        tvEndDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    endDate = date.time.time.toDisplayFormat()
                    this@StatAct.tvEndDate.text = "结束日期 $endDate"
                    getStat()
                }
            }
        }

        getStat()
    }

    private fun getStat(){
        v1Api.stat(
            beginDate = beginDate,
            endDate = endDate
        ).observeOnMain(this)
            .subscribeBy (
                onSuccess = {
                    //          simpleAdapter.setNewData(getData())
                },
                onError = {}
            )
    }

    private fun getData(): List<Stat> {
        val data = ArrayList<Stat>()
        data.add(Stat("陕西高院", 1000, 50, 80, 970, 7.6))
        data.add(Stat("西安中院", 1200, 800, 900, 1100, 45.0))
        data.add(Stat("铜川中院", 45, 50, 80, 15, 84.2))
        data.add(Stat("铁路中院", 100, 40, 140, 0, 100.0))
        data.add(Stat("陕西高院", 1000, 50, 80, 970, 7.6))
        data.add(Stat("西安中院", 1200, 800, 900, 1100, 45.0))
        data.add(Stat("铜川中院", 45, 50, 80, 15, 84.2))
        data.add(Stat("铁路中院", 100, 40, 140, 0, 100.0))
        data.add(Stat("陕西高院", 1000, 50, 80, 970, 7.6))
        data.add(Stat("西安中院", 1200, 800, 900, 1100, 45.0))
        data.add(Stat("铜川中院", 45, 50, 80, 15, 84.2))
        data.add(Stat("铁路中院", 100, 40, 140, 0, 100.0))
        data.add(Stat("陕西高院", 1000, 50, 80, 970, 7.6))
        data.add(Stat("西安中院", 1200, 800, 900, 1100, 45.0))
        data.add(Stat("铜川中院", 45, 50, 80, 15, 84.2))
        data.add(Stat("铁路中院", 100, 40, 140, 0, 100.0))
        data.add(Stat("合计", 2345, 940, 1200, 2085, 36.5))
        return data
    }

    override val layoutId = R.layout.act_stat

}