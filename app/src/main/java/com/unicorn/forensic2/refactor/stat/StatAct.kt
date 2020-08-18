package com.unicorn.forensic2.refactor.stat

import androidx.recyclerview.widget.LinearLayoutManager
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_stat.*
import org.joda.time.DateTime
import org.joda.time.LocalDate

class StatAct : BaseAct() {

    val localDate = LocalDate.now().dayOfMonth().withMinimumValue()

    val beginDate = DateTime().withDayOfMonth(1)

    val endDate = DateTime()

    val simpleAdapter = StatAdapter()

    override fun initViews() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatAct)
            simpleAdapter.bindToRecyclerView(this)
        }
    }

    override fun bindIntent() {
        titleBar.setTitle("收结存统计")
        tvBeginDate.text = "开始日期 ${beginDate.toString(displayDateFormat)}"
        tvEndDate.text = "结束日期 ${endDate.toString(displayDateFormat)}"
        simpleAdapter.setNewData(getData())
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