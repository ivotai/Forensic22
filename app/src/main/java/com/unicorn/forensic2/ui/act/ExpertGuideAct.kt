package com.unicorn.forensic2.ui.act

import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Expert
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_expert_guide.*

class ExpertGuideAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("专家")
    }

    override fun bindIntent() {
//        ivJgxx.clicks().mergeWith(tvJgxx.clicks()).subscribe {
//            Intent(this@ExpertGuideAct, JdjgMyEditAct::class.java).apply {
//                putExtra(Param, expert)
//            }.let { startActivity(it) }
//        }
//        ivJgzz.clicks().mergeWith(tvJgzz.clicks()).subscribe { startAct(JgzzMyListAct::class.java) }
//        ivJdry.clicks().mergeWith(tvJdry.clicks()).subscribe { startAct(JdryMyListAct::class.java) }

//        ivTjsh.clicks().mergeWith(tvTjsh.clicks()).subscribe { showSubmitAuditConfirm() }
//        ivShjl.clicks().mergeWith(tvShjl.clicks())
//            .subscribe { startAct(ShjlJdjgListAct::class.java) }
        getExpert()
    }

    private fun getExpert() {
        fun display() = with(expert) {
            tvExpertName.text = expertName
        }

        val mask = DialogHelper.showMask(this)
        v1Api.getExpert(user.expertId)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    mask.dismiss()
                    expert = it
                    display()
                },
                onError = {
                    mask.dismiss()
                    root.visibility = View.INVISIBLE
                    ToastUtils.showShort("获取专家信息失败")
                }
            )
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, RefreshEvent::class.java, Consumer {
            getExpert()
        })
    }

    private lateinit var expert: Expert

    override val layoutId = R.layout.act_expert_guide

}
