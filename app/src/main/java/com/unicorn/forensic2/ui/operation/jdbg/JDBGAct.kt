package com.unicorn.forensic2.ui.operation.jdbg

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.CaseType
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_jdbg.*

class JDBGAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("鉴定报告")

        case.jdLotteryTime?.run {
            tvDaysAppraisal.setText(daysAppraisal.toString())
            tvDaysPay.setText(daysPay.toString())
            tvDaysClearfee.setText(daysClearfee.toString())
            tvDaysCheck.setText(daysCheck.toString())
            tvDaysEvidence.setText(daysEvidence.toString())
        }
        tvRemark.setText(case.remark)
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

        // 假如是已鉴定什么都不能保存
        if (caseType == CaseType.YJD) return
        titleBar.setOperation("保存").safeClicks().subscribe {
            //
        }
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }
    private val caseType by lazy { intent.getSerializableExtra(com.unicorn.forensic2.app.CaseType) as CaseType }
    override val layoutId = R.layout.act_jdbg

}
