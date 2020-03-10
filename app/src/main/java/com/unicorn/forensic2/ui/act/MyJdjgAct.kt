package com.unicorn.forensic2.ui.act

import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.helper.DialogHelper
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_my_jdjg.*

class MyJdjgAct : BaseAct() {

    override fun initViews() {
        super.initViews()
        titleBar.setTitle("机构信息")
    }

    override fun bindIntent() {
        fun displayJdjg(jdjg: Jdjg) = with(jdjg) {
            tvJgmc.text = jgmc
        }

        fun getMyJdjg() {
            val mask = DialogHelper.showMask(this)
            v1Api.getMyJdjg()
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
        getMyJdjg()
    }

    override val layoutId = R.layout.act_my_jdjg

}
