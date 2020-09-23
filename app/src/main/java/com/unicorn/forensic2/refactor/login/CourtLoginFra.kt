package com.unicorn.forensic2.refactor.login

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.model.Fy
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.ui.base.BaseFra
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fra_court_login.*

class CourtLoginFra : BaseFra() {

    private var fy: Fy? = null

    override fun initViews() {
        fun restoreLoginInfo() = with(LoginInfo) {
            etUsername.setText(username)
            etPassword.setText(password)
        }
        restoreLoginInfo()
    }

    private fun getFy() {
        api.getFy().observeOnMain(this).subscribeBy {
            MaterialDialog(requireContext()).show {
                listItems(items = it.map { it.dmms }) { _, index, _ ->
                    fy = it[index]
                    this@CourtLoginFra.etCourt.setText(fy?.dmms ?: "")
                }
            }
        }
    }

    override fun bindIntent() {
        rtvLogin.safeClicks().subscribe {
            if (fy == null) {
                ToastUtils.showShort("请选择法院")
                return@subscribe
            }
            RxBus.post(
                LoginEvent(
                    username = etUsername.trimText(),
                    password = etPassword.trimText(),
                    court = fy?.dm
                )
            )
        }
        etCourt.safeClicks().subscribe { getFy() }
    }

    override val layoutId = R.layout.fra_court_login

}