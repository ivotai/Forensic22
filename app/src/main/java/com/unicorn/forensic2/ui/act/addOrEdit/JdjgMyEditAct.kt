package com.unicorn.forensic2.ui.act.addOrEdit

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.ui.act.CylyTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
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

        tvJgxz.safeClicks().subscribe {
            v1Api.getJgxz()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JdjgMyEditAct).show {
                            listItems(items = it.map { it.jgxz }) { _, index, _ ->
                                jdjg.jgxzId = it[index].jgxzId
                                this@JdjgMyEditAct.tvJgxz.text = it[index].jgxz
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取机构性质失败")
                    }
                )
        }
        tvZzlb.safeClicks().subscribe {
            v1Api.getZzlb()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JdjgMyEditAct).show {
                            listItems(items = it.map { it.zzlb }) { _, index, _ ->
                                jdjg.zzlbId = it[index].zzlbId
                                this@JdjgMyEditAct.tvZzlb.text = it[index].zzlb
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取资质等级失败")
                    }
                )
        }
        tvJgszd.safeClicks().subscribe { startAct(CylyTreeAct::class.java) }
        tvClsj.safeClicks().subscribe {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    jdjg.clsj = date.time.time
                    this@JdjgMyEditAct.tvClsj.text = jdjg.clsj.toDisplayFormat()
                }
            }
        }
        rtvNextStep.safeClicks().subscribe {
            Intent(this@JdjgMyEditAct, JdjgMyEditPictureAct::class.java).apply {
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
        etYb.textChanges().map { it.toString() }.subscribe { jdjg.yb = it }
        etZczj.textChanges().map { it.toString() }.subscribe { jdjg.zczj = it }
        etBankName.textChanges().map { it.toString() }.subscribe { jdjg.bankname = it }
        etBankAccount.textChanges().map { it.toString() }.subscribe { jdjg.bankaccount = it }
        etZwpj.textChanges().map { it.toString() }.subscribe { jdjg.zwpj = it }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Cyly -> {
                    jdjg.jgszdId = it.dict.id
                    tvJgszd.text = it.dict.name
                }
            }
        })
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            finish()
        })
    }

    private val jdjg by lazy { intent.getSerializableExtra(Param) as Jdjg }

    override val layoutId = R.layout.act_jdjg_my_edit

}