package com.unicorn.forensic2.refactor.login

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.LoginInfo
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.role.RoleWrapper
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_login2.*

class LoginAct2 : BaseAct() {

    override fun initViews() {
        initViewPager2()
    }

    private fun initViewPager2() {
        viewPager2.run {
            isUserInputEnabled = false
            offscreenPageLimit = LoginFragmentStateAdapter.titles.size - 1
            adapter = LoginFragmentStateAdapter(this@LoginAct2)
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, LoginEvent::class.java, Consumer {
            login(it)
        })
    }

    private fun login(loginEvent: LoginEvent) {
        fun storeLoginInfo(username: String, password: String) {
            LoginInfo.username = username
            LoginInfo.password = password
        }

        val mask = DialogHelper.showMask(this)
        v1Api.login(
            username = loginEvent.username,
            password = loginEvent.password
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
                    storeLoginInfo(loginEvent.username, loginEvent.password)

                    showRoleDialog()
                },
                onError = {
                    mask.dismiss()
                    ToastUtils.showShort("登录失败")
                }
            )
    }

    private fun showRoleDialog() {
        val roleTags = user.roles
        val roles = roleTags.map { Role.findByRoleTag(it) }
        val roleWrappers = RoleWrapper.getRoleWrappers(roles)

        // 假如只有一个角色
        if (roleWrappers.size == 1) {
            role = roleWrappers.first().role
            // 刷新登录状态
            RxBus.post(LoginStateChangeEvent())
            // 关闭登录界面
            finish()
            return
        }

        val cns = roleWrappers.map { it.cn }
        MaterialDialog(this).show {
            title(text = "选择角色")
            listItems(items = cns) { _, index, _ ->
                role = roleWrappers[index].role
                // 刷新登录状态
                RxBus.post(LoginStateChangeEvent())
                // 关闭登录界面
                finish()
            }
        }
    }

    override fun bindIntent() {
        tvUserLogin.clicks().subscribe {
            viewPager2.setCurrentItem(0, false)
            tvUserLogin.setTextColor(checkedColor)
            vUserLogin.setBackgroundColor(checkedColor)
            tvCourtLogin.setTextColor(unCheckedColor)
            vCourtLogin.setBackgroundColor(unCheckedColor)
        }
        tvCourtLogin.clicks().subscribe {
            viewPager2.setCurrentItem(1, false)
            tvCourtLogin.setTextColor(checkedColor)
            vCourtLogin.setBackgroundColor(checkedColor)
            tvUserLogin.setTextColor(unCheckedColor)
            vUserLogin.setBackgroundColor(unCheckedColor)
        }
    }

    override val layoutId = R.layout.act_login2

    private val unCheckedColor = ColorUtils.getColor(R.color.md_blue_300)
    private val checkedColor = ColorUtils.getColor(R.color.white)

}