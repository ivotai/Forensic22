package com.unicorn.forensic2.refactor.login

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.data.model.TreeResult2
import com.unicorn.forensic2.ui.act.FyTreeAct
import com.unicorn.forensic2.ui.base.BaseFra
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fra_court_login.*

class CourtLoginFra : BaseFra() {

    private var dm: String = ""
    private var dmms: String = ""

    override fun initViews() {
        fun restoreLoginInfo() = with(LoginInfo) {
            if (isCourtLogin) {
                etUsername.setText(username)
                etPassword.setText(password)
                etCourt.text = dmms
                this@CourtLoginFra.dm = dm
                this@CourtLoginFra.dmms = dmms
            }
        }
        restoreLoginInfo()
    }

    private fun getFy() {
        api.getFy().observeOnMain(this).subscribeBy {
            MaterialDialog(requireContext()).show {
                listItems(items = it.map { it.dmms }) { _, index, _ ->
                    val fy = it[index]
                    dm = fy.dm
                    dmms = fy.dmms
                    this@CourtLoginFra.etCourt.text = dmms
                }
            }
        }
    }

    override fun bindIntent() {
        rtvLogin.safeClicks().subscribe {
            if (dm.isEmpty()) {
                ToastUtils.showShort("请选择法院")
                return@subscribe
            }
            RxBus.post(
                LoginEvent(
                    username = etUsername.trimText(),
                    password = etPassword.trimText(),
                    dm = dm,
                    dmms = dmms
                )
            )
        }
        etCourt.safeClicks().subscribe { startAct(FyTreeAct::class.java) }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult2::class.java, Consumer {
            dm = it.fy.dm
            dmms = it.fy.dmms
            this@CourtLoginFra.etCourt.text = dmms
        })
    }

    override val layoutId = R.layout.fra_court_login

}