package com.unicorn.forensic2.ui.act

import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.model.param.ForgotPasswordParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_forgot_pwd.*
import java.util.concurrent.TimeUnit

class ForgotPwdAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("找回密码")
    }

    override fun bindIntent() {
        rtvConfirm.safeClicks().subscribe {
            if (etMobile.isEmpty()) {
                ToastUtils.showShort("手机号不能为空")
                return@subscribe
            }
            if (etVerifyCode.isEmpty()) {
                ToastUtils.showShort("验证码不能为空")
                return@subscribe
            }
            if (etNewPwd.isEmpty()) {
                ToastUtils.showShort("新密码不能为空")
                return@subscribe
            }
            if (etConfirmPwd.isEmpty()) {
                ToastUtils.showShort("确认密码不能为空")
                return@subscribe
            }
            if (etNewPwd.trimText() != etConfirmPwd.trimText()) {
                ToastUtils.showShort("密码不一致")
                return@subscribe
            }
            forgotPwd()
        }
        verifyCodeClicks()
    }

    private fun forgotPwd() {
        v1Api.forgotPassword(
            ForgotPasswordParam(
                phoneNo = etMobile.trimText(),
                verifyCode = etVerifyCode.trimText(),
                password = etNewPwd.trimText()
            )
        )
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    if (it.success) {
                        ToastUtils.showShort("修改成功")
                        finish()
                    } else
                        ToastUtils.showShort("修改失败")
                },
                onError = {
                }
            )
    }

    private fun verifyCodeClicks() {
        fun getVerifyCode() {
            v1Api.getVerifyCodeForForgotPassword(phoneNo = etMobile.trimText())
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        if (it.success)
                            ToastUtils.showShort("验证码已发送")
                        else
                            ToastUtils.showShort("获取验证码失败")
                    },
                    onError = {}
                )
        }
        tvVerifyCode.setOnClickListener { _ ->
            if (TextUtils.isEmpty(etMobile.trimText())) {
                ToastUtils.showShort("手机号不能为空")
                return@setOnClickListener
            }
            val count = verifyCodeCount
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map { count - it }
                .observeOnMain(this)
                .doOnSubscribe {
                    getVerifyCode()
                    tvVerifyCode.setOnClickListener(null)
                    tvVerifyCode.setBackgroundColor(
                        ContextCompat.getColor(
                            this@ForgotPwdAct,
                            R.color.md_grey_300
                        )
                    )
                }
                .subscribeBy(
                    onNext = {
                        tvVerifyCode.text = "已发送(${it}秒)"
                    },
                    onComplete = {
                        tvVerifyCode.text = "验证码"
                        tvVerifyCode.setBackgroundColor(
                            ContextCompat.getColor(
                                this@ForgotPwdAct,
                                R.color.colorPrimary
                            )
                        )
                        verifyCodeClicks()
                    },
                    onError = {
                    }
                )
        }
    }

    override val layoutId = R.layout.act_forgot_pwd

}