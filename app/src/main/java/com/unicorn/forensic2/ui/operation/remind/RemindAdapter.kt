package com.unicorn.forensic2.ui.operation.remind

import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat2
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_remind.*

class RemindAdapter : BaseQuickAdapter<Remind, KVHolder>(R.layout.item_remind) {

    override fun convert(helper: KVHolder, item: Remind) {
        helper.apply {
            tvRemindDttm.text = item.remindDttm.toDisplayFormat2()
            tvFromFlag.text = item.fromFlagStr
            tvToFlag.text = item.toFlagStr
            tvRemark.text = item.remark
        }
        helper.apply {
            tvFile.safeClicks().subscribe {
                if (item.fileInfo == null) ToastUtils.showShort("暂无文件")
                item.fileInfo?.open(context)
            }
        }
    }

}