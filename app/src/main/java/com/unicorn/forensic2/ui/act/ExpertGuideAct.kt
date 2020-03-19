package com.unicorn.forensic2.ui.act

import android.content.Intent
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Expert
import com.unicorn.forensic2.ui.act.addOrEdit.ExpertEditAct
import com.unicorn.forensic2.ui.act.list.ShjlExpertListAct
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

        tvBasicInfo.safeClicks().subscribe {
            Intent(this@ExpertGuideAct, ExpertEditAct::class.java).apply {
                putExtra(Param, expert)
            }.let { startActivity(it) }
        }
        tvShjl.safeClicks().subscribe { startAct(ShjlExpertListAct::class.java) }

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
