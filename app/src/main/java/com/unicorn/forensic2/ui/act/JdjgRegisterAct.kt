package com.unicorn.forensic2.ui.act

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.LoginStateChangeEvent
import com.unicorn.forensic2.data.model.param.RegisterJdjgParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_register.*

class JdjgRegisterAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("注册机构")
        v1Api.getPersonalInfo().observeOnMain(this).subscribe()
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
                    ToastUtils.showShort("获取机构性质失败")
                }
            )
    }

    override fun bindIntent() {
        tvJgxz.safeClicks().subscribe {
            v1Api.getJgxz()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JdjgRegisterAct).show {
                            listItems(items = it.map { it.jgxz }) { _, index, _ ->
                                jgxzId = it[index].jgxzId
                                this@JdjgRegisterAct.tvJgxz.text = it[index].jgxz
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取机构性质失败")
                    }
                )
        }

        fun registerJdjg() {
            v1Api.registerJdjg(
                RegisterJdjgParam(
                    jgmc = etJgmc.trimText(),
                    jgxzId = jgxzId,
                    yyzzh = etYyzzh.trimText(),
                    xkzh = etXkzh.trimText(),
                    fddb = etFddb.trimText(),
                    fddbSfzh = etFddbSfzh.trimText(),
                    lxr = etLxr.trimText(),
                    lxrDh = etLxrDh.trimText(),
                    lxrSj = etLxrSj.trimText(),
                    bgdz = etBgdz.trimText(),
                    yb = etYb.trimText()
                )
            ).observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        if (it.success) {
                            ToastUtils.showShort("注册成功")
                            getPersonalInfo()
                            finish()
                        } else
                            ToastUtils.showShort("注册失败")
                    },
                    onError = {
                        ToastUtils.showShort("注册失败")
                    }
                )
        }

        rtvRegister.safeClicks().subscribe {
            if (etJgmc.isEmpty()) {
                ToastUtils.showShort("机构名称不能为空")
                return@subscribe
            }
            if (jgxzId.isBlank()) {
                ToastUtils.showShort("请选择机构性质")
                return@subscribe
            }
            if (etYyzzh.isEmpty()) {
                ToastUtils.showShort("营业执照号不能为空")
                return@subscribe
            }
            if (etJgmc.isEmpty()) {
                ToastUtils.showShort("机构名称不能为空")
                return@subscribe
            }
            if (etXkzh.isEmpty()) {
                ToastUtils.showShort("许可证号不能为空")
                return@subscribe
            }
            if (etFddb.isEmpty()) {
                ToastUtils.showShort("法人代表不能为空")
                return@subscribe
            }
            if (etFddbSfzh.isEmpty()) {
                ToastUtils.showShort("法人代表身份证号不能为空")
                return@subscribe
            }
            if (etLxr.isEmpty()) {
                ToastUtils.showShort("联系人姓名不能为空")
                return@subscribe
            }
            if (etLxrDh.isEmpty()) {
                ToastUtils.showShort("联系人电话不能为空")
                return@subscribe
            }
            if (etLxrSj.isEmpty()) {
                ToastUtils.showShort("联系人手机不能为空")
                return@subscribe
            }
            if (etBgdz.isEmpty()) {
                ToastUtils.showShort("办公地址不能为空")
                return@subscribe
            }
            if (etYb.isEmpty()) {
                ToastUtils.showShort("邮编不能为空")
                return@subscribe
            }
            registerJdjg()
        }

    }

    override val layoutId = R.layout.act_jdjg_register

    var jgxzId = ""

}