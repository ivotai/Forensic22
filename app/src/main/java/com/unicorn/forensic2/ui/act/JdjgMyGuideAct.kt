package com.unicorn.forensic2.ui.act

import android.content.Intent
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Param
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.act.addOrEdit.JdjgMyEditAct
import com.unicorn.forensic2.ui.act.list.JdryMyListAct
import com.unicorn.forensic2.ui.act.list.JgzzMyListAct
import com.unicorn.forensic2.ui.act.list.ShjlJdjgListAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my_guide.*

class JdjgMyGuideAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("我的机构")
    }

    override fun bindIntent() {
        ivJgxx.clicks().mergeWith(tvJgxx.clicks()).subscribe {
            Intent(this@JdjgMyGuideAct, JdjgMyEditAct::class.java).apply {
                putExtra(Param, jdjg)
            }.let { startActivity(it) }
        }
        ivJgzz.clicks().mergeWith(tvJgzz.clicks()).subscribe { startAct(JgzzMyListAct::class.java) }
        ivJdry.clicks().mergeWith(tvJdry.clicks()).subscribe { startAct(JdryMyListAct::class.java) }

        fun showSubmitAuditConfirm() {
            fun submitAudit() {
                ComponentHolder.appComponent.v1Api()
                    .submitAudit()
                    .observeOnMain(this)
                    .subscribeBy(
                        onSuccess = {
                            if (!it.success) {
                                ToastUtils.showShort("提交审核失败")
                                return@subscribeBy
                            }
                            RxBus.post(RefreshEvent())
                        },
                        onError = {
                            ToastUtils.showShort("提交审核失败")
                        }
                    )
            }

            MaterialDialog(this).show {
                title(text = "确认提交审核？")
                positiveButton(text = "确认") { submitAudit() }
            }
        }
        ivTjsh.clicks().mergeWith(tvTjsh.clicks()).subscribe { showSubmitAuditConfirm() }
        ivShjl.clicks().mergeWith(tvShjl.clicks()).subscribe {
            Intent(this, ShjlJdjgListAct::class.java).apply {
                putExtra(Param, jdjg.jgid)
            }.let { startActivity(it) }
        }
        getJdjgMy()
    }

    private fun getJdjgMy() {
        fun display() = with(jdjg) {
            tvJgmc.text = jgmc
        }

        val mask = DialogHelper.showMask(this)
        v1Api.getJdjgMy()
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    jdjg = it
                    display()
                },
                onError = {
                    mask.dismiss()
                    root.visibility = View.INVISIBLE
                    ToastUtils.showShort("获取机构信息失败")
                }
            )
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            getJdjgMy()
        })
    }

    private lateinit var jdjg: Jdjg

    override val layoutId = R.layout.act_jdjg_my_guide

}
