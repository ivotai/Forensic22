package com.unicorn.forensic2.ui.act.list

import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.param.UpdatePersonalInfoParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_register.titleBar
import kotlinx.android.synthetic.main.act_personal_info_update.*

class PersonalInfoUpdateAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("修改个人信息")
    }

    private fun getPersonalInfo() {
        v1Api.getPersonalInfo().observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    if (it.success) {
                        loginResult.user = it.user
                        RxBus.post(LoginStateChangeEvent())
                    }
                },
                onError = {
                }
            )
    }

    override fun bindIntent() {
        fun updatePersonalInfo() {
            v1Api.updatePersonalInfo(
                UpdatePersonalInfoParam(
                    name = etName.trimText(),
                    description = "1_${etDescription.trimText()}"
                )
            ).observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        if (it.success) {
                            ToastUtils.showShort("修改成功")
                            getPersonalInfo()
                            finish()
                        } else
                            ToastUtils.showShort("修改失败")
                    },
                    onError = {
                        ToastUtils.showShort("修改失败")
                    }
                )
        }

        rtvUpdate.safeClicks().subscribe {
            if (etName.isEmpty()) {
                ToastUtils.showShort("姓名不能为空")
                return@subscribe
            }
            if (etDescription.isEmpty()) {
                ToastUtils.showShort("身份证号不能为空")
                return@subscribe
            }
            updatePersonalInfo()
        }

    }

    override val layoutId = R.layout.act_personal_info_update

}