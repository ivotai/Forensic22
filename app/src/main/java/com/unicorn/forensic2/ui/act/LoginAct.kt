package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override fun initViews() {
        fun restoreLoginInfo() = with(LoginInfo) {
            etUsername.setText(username)
            etPassword.setText(password)
        }
        restoreLoginInfo()
    }

    override fun bindIntent() {
        rtvLogin.safeClicks().subscribe { login() }
        tvRegister.safeClicks().subscribe { startAct(RegisterAct::class.java) }
        tvForgotPwd.safeClicks().subscribe { startAct(ForgotPwdAct::class.java) }
    }

    private fun login() {
        fun storeLoginInfo(username: String, password: String) {
            LoginInfo.username = username
            LoginInfo.password = password
        }

        val mask = DialogHelper.showMask(this)
        v1Api.login(
            username = etUsername.trimText(),
            password = etPassword.trimText()
        ).observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    if (!it.success){
                        ToastUtils.showShort("登录失败")
                        return@subscribeBy
                    }
                    isLogin = true
                    loginResult = it
                    storeLoginInfo(etUsername.trimText(), etPassword.trimText())
                    // 关闭登录界面
                    finish()
                    // 刷新登录状态
                    RxBus.post(LoginStateChangeEvent())
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("登录失败")
                }
            )
    }

    override val layoutId = R.layout.act_login

}