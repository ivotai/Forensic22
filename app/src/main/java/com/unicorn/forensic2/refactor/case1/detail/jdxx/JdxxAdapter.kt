package com.unicorn.forensic2.refactor.case1.detail.jdxx

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdxx.*

class JdxxAdapter : BaseQuickAdapter<Jdxx, KVHolder>(R.layout.item_jdxx) {

    override fun convert(helper: KVHolder, item: Jdxx) {

        helper.apply {

            tvJgmc.text = item.jgmc
            tvDateLottery.text = item.dateLottery.toDisplayFormat()
            tvLotteryStatus.text = item.lotteryStatusStr
            tvJdlb.text = item.jdlb
            tvJdryxm.text = item.jdryxm
            tvFee.text = item.fee.toString()

            //

            if (item.fidzbtz != null) {
                tvZbtz.text = "查看中标通知"
                tvZbtz.safeClicks().subscribe { item.fidzbtz.open(context) }
            } else {
                tvZbtz.text = ""
            }

            if (item.fidyhhfInfo != null) {
                tvYhhf.text = "查看摇号回放"
                tvYhhf.safeClicks().subscribe { item.fidyhhfInfo.open(context) }
            } else {
                tvYhhf.text = ""
            }

            if (item.fidjdbgInfo != null) {
                tvJdbg.text = "查看鉴定报告"
                tvJdbg.safeClicks().subscribe { item.fidjdbgInfo.open(context) }
            } else {
                tvJdbg.text = ""
            }

            if (item.fidjfqdInfo != null) {
                tvJfqd.text = "查看缴费清单"
                tvJfqd.safeClicks().subscribe { item.fidjfqdInfo.open(context) }
            } else {
                tvJfqd.text = ""
            }

            if (item.fidsfbzInfo != null) {
                tvSfbz.text = "查看收费标准"
                tvSfbz.safeClicks().subscribe { item.fidsfbzInfo.open(context) }
            } else {
                tvSfbz.text = ""
            }

        }

    }

}