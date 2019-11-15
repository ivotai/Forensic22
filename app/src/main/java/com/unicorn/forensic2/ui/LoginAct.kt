package com.unicorn.forensic2.ui


import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.LoginStateChange
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.data.model.UserLogin
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_login.*

class LoginAct : BaseAct() {

    override fun initViews() {
        etLoginStr.setText(LoginInfo.loginStr)
        etPwd.setText(LoginInfo.pwd)
    }

    override fun bindIntent() {
        rtvLogin.safeClicks().subscribe { login() }
//        tvRegister.safeClicks().subscribe { startAct(RegisterAct::class.java) }
//        tvRetrievePwd.safeClicks().subscribe { startAct(RetrievePwdAct::class.java) }
    }

    private fun login() {
        val mask = DialogHelper.showMask(this)
        v1Api.login(
            UserLogin(
                loginStr = etLoginStr.trimText(),
                userPwd = etPwd.trimText().encrypt()
            )
        ).observeOnMain(this)
            .subscribeBy(
                onNext = {
                    mask.dismiss()
                    if (it.failed) return@subscribeBy
                    isLogin = true
                    userLoginResult = it.data
                    RxBus.post(LoginStateChange())
                    LoginInfo.loginStr = etLoginStr.trimText()
                    LoginInfo.pwd = etPwd.trimText()
                    finish()
//                fakeLogin()
                },
                onError = {
                    mask.dismiss()
                }
            )
    }

//    private fun fakeLogin() {
//        ComponentHolder.appComponent.getV2Api()
//            .userFakeLogin()
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                Global.sid2 = it.data.sid
//            }
//    }

    override val layoutId = R.layout.act_login

}