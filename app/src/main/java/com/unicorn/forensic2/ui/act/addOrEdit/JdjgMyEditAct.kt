package com.unicorn.forensic2.ui.act.addOrEdit

import android.content.Intent
import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.BaseAct
import kotlinx.android.synthetic.main.act_jdjg_my_edit.*

class JdjgMyEditAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("编辑我的机构")
    }

    override fun bindIntent() {
        fun display() = with(jdjg) {
            etJgmc.setText(jgmc)
            etJgmc2.setText(jgmc2)
            tvJgxz.setText(jgxz)
            tvZzlb.setText(zzlb)
            etYyzzh.setText(yyzzh)
            etFddb.setText(fddb)
            etFddbSfzh.setText(fddbSfzh)
            etFzr.setText(fzr)
            etFzrDh.setText(fzrDh)
            etFzrSj.setText(fzrSj)
            etLxr.setText(lxr)
            etLxrDh.setText(lxrDh)
            etLxrSj.setText(lxrSj)
            etLxrCz.setText(lxrCz)
            etLxrYx.setText(lxrYx)
            tvJgszd.setText(jgszd)
            etBgdz.setText(bgdz)
            etYb.setText(yb)
            etZczj.setText(zczj)
            etBankName.setText(bankname)
            etBankAccount.setText(bankaccount)
            tvClsj.setText(clsj.toDisplayFormat())
            etZwpj.setText(zwpj)
        }
        display()

        rtvNextStep.safeClicks().subscribe {
            Intent(this@JdjgMyEditAct, JdryMyEditPictureAct::class.java).apply {
                putExtra(Param, jdjg)
            }.let { startActivity(it) }
        }

        etJgmc.textChanges().map { it.toString() }.subscribe { jdjg.jgmc = it }
        etJgmc2.textChanges().map { it.toString() }.subscribe { jdjg.jgmc2 = it }
        etYyzzh.textChanges().map { it.toString() }.subscribe { jdjg.yyzzh = it }
        etXkzh.textChanges().map { it.toString() }.subscribe { jdjg.xkzh = it }
        etFddb.textChanges().map { it.toString() }.subscribe { jdjg.fddb = it }
        etFddbSfzh.textChanges().map { it.toString() }.subscribe { jdjg.fddbSfzh = it }
        etFzr.textChanges().map { it.toString() }.subscribe { jdjg.fzr = it }
        etFzrDh.textChanges().map { it.toString() }.subscribe { jdjg.fzrDh = it }
        etFzrSj.textChanges().map { it.toString() }.subscribe { jdjg.fzrSj = it }
        etLxr.textChanges().map { it.toString() }.subscribe { jdjg.lxr = it }
        etLxrDh.textChanges().map { it.toString() }.subscribe { jdjg.lxrDh = it }
        etLxrSj.textChanges().map { it.toString() }.subscribe { jdjg.lxrSj = it }
        etLxrCz.textChanges().map { it.toString() }.subscribe { jdjg.lxrCz = it }
        etLxrYx.textChanges().map { it.toString() }.subscribe { jdjg.lxrYx = it }
        etBgdz.textChanges().map { it.toString() }.subscribe { jdjg.bgdz = it }
        etYb.textChanges().map { it.toString() }.subscribe { jdjg.yb= it }
        etZczj.textChanges().map { it.toString() }.subscribe { jdjg.zczj= it }
        etBankName.textChanges().map { it.toString() }.subscribe { jdjg.bankname = it }
        etBankAccount.textChanges().map { it.toString() }.subscribe { jdjg.bankaccount= it }
        etZwpj.textChanges().map { it.toString() }.subscribe { jdjg.zwpj= it }
    }

    private val jdjg by lazy { intent.getSerializableExtra(Param) as Jdjg }

    override val layoutId = R.layout.act_jdjg_my_edit

}