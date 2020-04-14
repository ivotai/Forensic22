package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.ZjCase
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_zj_case.*

class ZjCaseAdapter : BaseQuickAdapter<ZjCase, KVHolder>(R.layout.item_zj_case) {

    override fun convert(helper: KVHolder, item: ZjCase) {
        helper.apply {
            with(item.review) {
                tvRemark.text = remark
                tvBeginDate.text = beginDate.toDisplayFormat()
                tvEndDate.text = endDate.toDisplayFormat()
                tvPscl.text = fileName
            }
        }
    }

}