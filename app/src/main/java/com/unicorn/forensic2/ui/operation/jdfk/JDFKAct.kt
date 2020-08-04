package com.unicorn.forensic2.ui.operation.jdfk

import android.content.Intent
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Case
import com.unicorn.forensic2.data.model.Operation
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_jdfk.*

class JDFKAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle(Operation.JDFK.cn)
    }

    override fun bindIntent() {
        tvJdryxm.safeClicks().subscribe {
            Intent(this@JDFKAct, JdrySListAct::class.java).apply {
                putExtra(Param, case)
            }.let { startActivity(it) }
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, JdrySelectEvent::class.java, Consumer { jdrySelectEvent ->
            jdryList = jdrySelectEvent.jdryList
            tvJdryxm.text = jdryList!!.map { it.xm }.joinToString(",")
        })
    }

    override val layoutId = R.layout.act_jdfk

    private var jdryList: List<JdrySimple>? = null

    private val case by lazy { intent.getSerializableExtra(Param) as Case }

}