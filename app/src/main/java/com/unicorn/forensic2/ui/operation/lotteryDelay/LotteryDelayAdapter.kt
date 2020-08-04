package com.unicorn.forensic2.ui.operation.lotteryDelay

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_lottery_delay.*

class LotteryDelayAdapter : BaseQuickAdapter<LotteryDelay, KVHolder>(R.layout.item_lottery_delay) {

    override fun convert(helper: KVHolder, item: LotteryDelay) {
        helper.apply {
            tvApplydttm.text = item.applyDttm.toDisplayFormat()
            tvApplystate.text = item.applyState.toString()
            tvFee.text = item.fee.toString()
            tvJdryxm.text = item.jdryxm
            tvTerminate.text = item.terminate.toString()
        }

    }

}