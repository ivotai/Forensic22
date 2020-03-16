package com.unicorn.forensic2.ui.act

import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.startAct
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.act.list.JdryMyListAct
import com.unicorn.forensic2.ui.act.list.JgzzMyListAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_my.*

class JdjgMyAct : BaseAct() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构信息")
    }

    override fun bindIntent() {
        fun displayJdjg(jdjg: Jdjg) = with(jdjg) {
            tvJgmc.text = jgmc
        }

        fun getJdjgMy() {
            val mask = DialogHelper.showMask(this)
            v1Api.getJdjgMy()
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        mask.dismiss()
                        displayJdjg(it)
                    },
                    onError = {
                        mask.dismiss()
                        root.visibility = View.INVISIBLE
                        ToastUtils.showShort("获取机构信息失败")
                    }
                )
        }
        getJdjgMy()

        ivJgzz.clicks().mergeWith(tvJgzz.clicks()).subscribe { startAct(JgzzMyListAct::class.java) }
        ivJdry.clicks().mergeWith(tvJdry.clicks()).subscribe { startAct(JdryMyListAct::class.java) }
    }

    override val layoutId = R.layout.act_jdjg_my

}
