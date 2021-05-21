package com.unicorn.forensic2.refactor.stat

import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.data.model.TreeResult2
import com.unicorn.forensic2.ui.act.FyTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_stat.*
import org.joda.time.DateTime
import java.util.*
import kotlin.collections.ArrayList

class StatAct : BaseAct() {

    private var beginDate = DateTime().withDayOfMonth(1)

    private var endDate = DateTime()

    private var dm: String = LoginInfo.dm

    val simpleAdapter = StatAdapter()

    override fun initViews() {
        titleBar.setTitle("收结存统计")
        tvBeginDate.text = "开始日期 ${beginDate.toString(displayDateFormat)}"
        tvEndDate.text = "结束日期 ${endDate.toString(displayDateFormat)}"

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatAct)
            simpleAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        tvBeginDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker(
                    currentDate = beginDate.toCalendar(Locale.CHINA)
                ) { _, date ->
                    beginDate = DateTime(date)
                    this@StatAct.tvBeginDate.text = "开始日期 ${beginDate.toString(displayDateFormat)}"
                    getStat()
                }
            }
        }

        tvEndDate.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker(
                    currentDate = endDate.toCalendar(Locale.CHINA)
                ) { _, date ->
                    endDate = DateTime(date)
                    this@StatAct.tvEndDate.text = "结束日期 ${endDate.toString(displayDateFormat)}"
                    getStat()
                }
            }
        }

        titleBar.setOperation("法院").safeClicks().subscribe { startAct(FyTreeAct::class.java) }

        getStat()
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult2::class.java, Consumer {
            dm = it.fy.dm
            getStat()
        })
    }

    private fun getStat() {
        v1Api.stat(
            beginDate = beginDate.toString(displayDateFormat),
            endDate = endDate.toString(displayDateFormat),
            fydm = dm
        ).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    simpleAdapter.setNewData(it)
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