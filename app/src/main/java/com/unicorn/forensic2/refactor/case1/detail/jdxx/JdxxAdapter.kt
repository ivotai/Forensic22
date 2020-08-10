package com.unicorn.forensic2.refactor.case1.detail.jdxx

import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_jdxx.*

class JdxxAdapter : BaseQuickAdapter<Jdxx, KVHolder>(R.layout.item_jdxx) {

    override fun convert(helper: KVHolder, item: Jdxx) {
        helper.apply {
            tvLotteryStatus.text = item.lotteryStatusStr
            tvJdlb.text = item.jdlb
            tvDateLottery.text = item.dateLottery.toDisplayFormat()
            tvJgmc.text = item.jgmc
            tvFee.text = item.fee.toString()
            tvJdryxm.text = item.jdryxm
            tvJfqd.text = "查看缴费清单"
            tvSfbz.text = "查看收费标准"
            tvZbtz.text = "查看中标通知"
            tvYhhf.text = "查看摇号回放"
            tvJdbg.text = "查看鉴定报告"

            //
            tvJfqd.safeClicks().subscribe {
                if (item.fidjfqdInfo == null) ToastUtils.showShort("暂无文件")
                item.fidjfqdInfo?.open(context)
            }

            tvSfbz.safeClicks().subscribe {
                if (item.fidsfbzInfo == null) ToastUtils.showShort("暂无文件")
                item.fidsfbzInfo?.open(context)
            }

            tvZbtz.safeClicks().subscribe {
                if (item.fidzbtz == null) ToastUtils.showShort("暂无文件")
                item.fidzbtz?.open(context)
            }

            tvYhhf.safeClicks().subscribe {
                if (item.fidyhhfInfo == null) ToastUtils.showShort("暂无文件")
                item.fidyhhfInfo?.open(context)
            }

            tvJdbg.safeClicks().subscribe {
                if (item.fidjdbgInfo == null) ToastUtils.showShort("暂无文件")
                item.fidjdbgInfo?.open(context)
            }
        }

    }


}