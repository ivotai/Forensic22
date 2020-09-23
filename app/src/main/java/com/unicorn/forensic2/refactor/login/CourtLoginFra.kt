package com.unicorn.forensic2.refactor.login

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.ui.base.BaseFra
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fra_court_login.*

class CourtLoginFra : BaseFra() {

    override fun initViews() {
        fun restoreLoginInfo() = with(LoginInfo) {
            etUsername.setText(username)
            etPassword.setText(password)
        }
        restoreLoginInfo()
    }

    private fun getFy(){
        api.getFy().observeOnMain(this).subscribeBy {
            MaterialDialog(requireContext()).show {
                listItems(items = it.map { it.dmms }) { dialog, index, text ->

                }
            }
        }
    }

    override fun bindIntent() {
        rtvLogin.safeClicks().subscribe {
            RxBus.post(
                LoginEvent(
                    username = etUsername.trimText(),
                    password = etPassword.trimText()
                )
            )
        }

        getFy()
    }

    override val layoutId = R.layout.fra_court_login

}