package com.unicorn.forensic2.refactor.login

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.ui.act.ForgotPwdAct
import com.unicorn.forensic2.ui.act.RegisterAct
import com.unicorn.forensic2.ui.base.BaseFra
import kotlinx.android.synthetic.main.act_login.*

class LoginFra : BaseFra() {

    override fun initViews() {
        fun restoreLoginInfo() = with(LoginInfo) {
            if (!isCourtLogin) {
                etUsername.setText(username)
                etPassword.setText(password)
            }
        }
        restoreLoginInfo()
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
        tvRegister.safeClicks().subscribe { startAct(RegisterAct::class.java) }
        tvForgotPwd.safeClicks().subscribe { startAct(ForgotPwdAct::class.java) }
    }

    override val layoutId = R.layout.fra_login

}