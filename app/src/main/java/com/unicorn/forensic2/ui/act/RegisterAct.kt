package com.unicorn.forensic2.ui.act

import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.trimText
import com.unicorn.forensic2.app.verifyCodeCount
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_register.*
import java.util.concurrent.TimeUnit

class RegisterAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("注册")
        etMobile.setText("13611840424")
    }

    override fun bindIntent() {
        rtvRegister.safeClicks().subscribe { }
        verifyCodeClicks()
    }

//    private fun checkVerifyCode() {
//        userApi.checkVerifyCode(
//            VerifyCode(
//                userMobile = etMobile.trimText(),
//                code = etVerifyCode.trimText()
//            )
//        ).observeOnMain(this).subscribeBy(
//            onSuccess = {
//                if (it.failed) return@subscribeBy
//                register()
//            },
//            onError = {
//                it.print()
//            }
//        )
//    }

    private fun register() {
//        userApi.addUser(
//            UserInfo(
//                loginName = etLoginName.trimText(),
//                userMobile = etMobile.trimText(),
//                userPwd = EncryptUtils.encryptMD5ToString(etPwd.trimText()).toLowerCase(),
//                userIdNum = etUserIdNum.trimText()
//            )
//        ).observeOnMain(this).subscribeBy(
//            onSuccess = {
//                if (it.failed) return@subscribeBy
//                ToastUtils.showShort("注册完成")
//                finish()
//            },
//            onError = {
//                it.print()
//            }
//        )
    }

    private fun verifyCodeClicks() {
        fun getVerifyCode() {
            v1Api.getVerifyCodeForRegister(phoneNo = etMobile.trimText())
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        //                        if (it.failed) return@subscribeBy
                        if (it.success)
                            ToastUtils.showShort("验证码已发送")
                        else
                            ToastUtils.showShort("获取验证码失败")
//                        etVerifyCode.setText(it.data.value)
                    },
                    onError = {
                    }
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
                            this@RegisterAct,
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
                                this@RegisterAct,
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

    override val layoutId = R.layout.act_register

}