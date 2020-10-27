package com.unicorn.forensic2.ui.operation.jdbg

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_jdbg.*

class JDBGAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("鉴定报告")

        tvRemark.text = case.remark

        case.jdLotteryTime?.run {
            tvDaysAppraisal.text = daysAppraisal.toString()
            tvDaysPay.text = daysPay.toString()
            tvDaysClearfee.text = daysClearfee.toString()
            tvDaysCheck.text = daysCheck.toString()
            tvDaysEvidence.text = daysEvidence.toString()
        }
    }

    override fun bindIntent() {
        tvJdbg.safeClicks().subscribe {
            if (case.fidjdbgInfo == null) ToastUtils.showShort("暂无文件")
            case.fidjdbgInfo?.open(this)
        }
        tvJdwd.safeClicks().subscribe {
            if (case.fidjdwdInfo == null) ToastUtils.showShort("暂无文件")
            case.fidjdwdInfo?.open(this)
        }
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.act_jdbg

}
