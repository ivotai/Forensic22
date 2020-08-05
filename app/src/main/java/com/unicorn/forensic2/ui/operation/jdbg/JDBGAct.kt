package com.unicorn.forensic2.ui.operation.jdbg

import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.ui.act.PdfAct
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_jdbg.*

class JDBGAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("鉴定报告")

        tvRemark.text = case.remark
    }

    override fun bindIntent() {
        tvJdbg.safeClicks().subscribe {
            if (case.fidjdbg == null) {
                ToastUtils.showShort("暂无数据")
                return@subscribe
            }
            Intent(this@JDBGAct, PdfAct::class.java).apply {
                putExtra(Param, case.fidjdbg)
            }.let {
                startActivity(it)
            }
        }
        tvJdwd.safeClicks().subscribe {
            if (case.fidjdwd == null) {
                ToastUtils.showShort("暂无数据")
                return@subscribe
            }
            Intent(this@JDBGAct, PdfAct::class.java).apply {
                putExtra(Param, case.fidjdwd)
            }.let {
                startActivity(it)
            }
        }
    }

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

    override val layoutId = R.layout.act_jdbg

}
