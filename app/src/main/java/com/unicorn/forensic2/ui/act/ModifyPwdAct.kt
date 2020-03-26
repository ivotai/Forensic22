package com.unicorn.forensic2.ui.act

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.LogoutEvent
import com.unicorn.forensic2.data.model.param.ModifyPasswordParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_modify_pwd.*

class ModifyPwdAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("修改密码")
    }

    override fun bindIntent() {
        titleBar.setOperation("保存").safeClicks().subscribe { modifyPwdX() }
    }

    private fun modifyPwdX() {
        fun modifyPwd() {
            v1Api.modifyPassword(
                ModifyPasswordParam(
                    originPassword = etOldPwd.trimText(),
                    newPassword = etNewPwd.trimText()
                )
            ).observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        if (it.success) {
                            ToastUtils.showShort("修改密码成功，请重新登录")
                            RxBus.post(LogoutEvent())
                            finish()
                        }
                        else
                            ToastUtils.showShort("修改密码失败")
                    },
                    onError ={

                    }
                )
        }

        if (etOldPwd.isEmpty()) {
            ToastUtils.showShort("旧密码不能为空")
            return
        }
        if (etNewPwd.isEmpty()) {
            ToastUtils.showShort("新密码不能为空")
            return
        }
        if (etConfirmPwd.isEmpty()) {
            ToastUtils.showShort("确认密码不能为空")
            return
        }
        if (etConfirmPwd.trimText() != etNewPwd.trimText()) {
            ToastUtils.showShort("密码不一致")
            return
        }
        modifyPwd()
    }

    override val layoutId = R.layout.act_modify_pwd

}