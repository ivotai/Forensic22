package com.unicorn.forensic2.ui.act.addOrEdit

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.addOrEdit.ZjzzAddParam
import com.unicorn.forensic2.ui.act.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_zjzz_add_or_edit.*

class ZjzzAddAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("添加专家资质")
    }

    override fun bindIntent() {
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        etGrzc.textChanges().map { it.toString() }.subscribe { zjzzAddParam.grzc = it }
        etZczyh.textChanges().map { it.toString() }.subscribe { zjzzAddParam.zczyh = it }
        tvZyzsyxq.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    val dateStr = date.time.time.toDisplayFormat()
                    zjzzAddParam.zyzsyxq = dateStr
                    this@ZjzzAddAct.tvZyzsyxq.text = dateStr
                }
            }
        }

        fun save() = with(zjzzAddParam) {

        }

        titleBar.setOperation("保存").safeClicks().subscribe {
            with(zjzzAddParam) {
                if (jdlbId.isBlank()) {
                    ToastUtils.showShort("请选择鉴定类别")
                    return@subscribe
                }
                if (zczyh.isBlank()) {
                    ToastUtils.showShort("请选择注册执业号")
                    return@subscribe
                }
                if (grzc.isBlank()) {
                    ToastUtils.showShort("请选择个人职称")
                    return@subscribe
                }
                if (zyzsyxq.isBlank()) {
                    ToastUtils.showShort("请选择有效日期")
                    return@subscribe
                }
            }
            save()
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            zjzzAddParam.jdlbId = it.dict.id
            tvJdlb.text = it.dict.name
        })
    }

    private val zjzzAddParam = ZjzzAddParam()

    override val layoutId = R.layout.act_zjzz_add_or_edit

}