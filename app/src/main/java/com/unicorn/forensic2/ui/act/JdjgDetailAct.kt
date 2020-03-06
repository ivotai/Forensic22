package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.view.clicks
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.Jdjg
import com.unicorn.forensic2.app.JdjgId
import com.unicorn.forensic2.app.displayDateFormat
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.data.model.Jdjg
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_detail.*
import org.joda.time.DateTime
import java.util.*

class JdjgDetailAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("机构详情")
    }

    override fun bindIntent() {
        getJdjgDetail()
    }

    private fun getJdjgDetail() {
        fun startJgzzListAct() {
            Intent(this, JgzzListAct::class.java).apply {
                putExtra(Jdjg, jdjg)
            }.let { startActivity(it) }
        }

        fun startJdryListAct() {
            Intent(this, JdryListAct::class.java).apply {
                putExtra(Jdjg, jdjg)
            }.let { startActivity(it) }
        }

        v1Api.getJdjgDetail(jdjgId = jdjgId)
            .observeOnMain(this)
            .subscribeBy(
                onSuccess = {
                    jdjg = it
                    tvJgzz.clicks().mergeWith(ivJgzz.clicks()).subscribe { startJgzzListAct() }
                    tvJdry.clicks().mergeWith(ivJdry.clicks()).subscribe { startJdryListAct() }
                    displayDetail()
                },
                onError = {
                    ToastUtils.showShort("获取机构详情失败")
                }
            )
    }

    private fun displayDetail() {
        with(jdjg) {
            tvJgmc.text = jgmc
            tvJgxz.text = jgxz
            tvYyzzh.text = yyzzh
            tvXkzh.text = xkzh
            tvFrdb.text = fddb
            tvFzr.text = fzr
            tvLxr.text = lxr
            tvLxrdh.text = lxrDh
            tvLxrcz.text = lxrCz
            tvLxrxy.text = lxrYx
            tvBgdz.text = bgdz
            tvYb.text = yb
            if (clsj != 0L)
                tvClrq.text = DateTime(Date(clsj)).toString(displayDateFormat)
            tvZczj.text = zczj
        }
    }

    private lateinit var jdjg: Jdjg

    private val jdjgId by lazy { intent.getStringExtra(JdjgId) }

    override val layoutId = R.layout.act_jdjg_detail

}