package com.unicorn.forensic2.ui.act

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.data.model.Role
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
                    if (!it.success) {
                        ToastUtils.showShort("登录失败")
                        return@subscribeBy
                    }
                    isLogin = true
                    loginResult = it
                    storeLoginInfo(etUsername.trimText(), etPassword.trimText())

                    showRoleDialog()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("登录失败")
                }
            )
    }

    private fun showRoleDialog() {
        val ens = user.roles

        // 假如只有一个角色
        if (ens.size == 1) {
            user.roleTag = ens.first()
            // 刷新登录状态
            RxBus.post(LoginStateChangeEvent())
            // 关闭登录界面
            finish()
            return
        }

        val cns = ens.map { Role.en2Cn(it) }
        MaterialDialog(this).show {
            title(text = "选择角色")
            listItems(items = cns) { _, index, _ ->
                user.roleTag = ens[index]
                // 刷新登录状态
                RxBus.post(LoginStateChangeEvent())
                // 关闭登录界面
                finish()
            }
        }
    }

    override val layoutId = R.layout.act_login

}