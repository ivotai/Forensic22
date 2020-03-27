package com.unicorn.forensic2.ui.act.query

import android.annotation.SuppressLint
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.QueryMapEvent
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.ui.act.tree.CylyTreeAct
import com.unicorn.forensic2.ui.act.tree.JdlbTreeAct
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jdjg_public_query.*

@SuppressLint("Registered")
class JdjgPublicQueryAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("搜索")
    }

    override fun bindIntent() {
        tvCyly.safeClicks().subscribe { startAct(CylyTreeAct::class.java) }
        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }

        fun showZzdjDialog() {
            if (jdlbId.isBlank()) {
                ToastUtils.showShort("请先选择鉴定类别")
                return
            }
            v1Api.getZzdj(jdlbId = jdlbId)
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JdjgPublicQueryAct).show {
                            listItems(items = it.map { it.name }) { _, index, _ ->
                                zzdjId = it[index].id
                                this@JdjgPublicQueryAct.tvZzdj.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取资质等级失败")
                    }
                )
        }
        tvZzdj.safeClicks().subscribe { showZzdjDialog() }

        titleBar.setOperation("确认").safeClicks().subscribe {
            val queryMap = HashMap<String, Any>()
            if (etJgmc.isNotBlack())
                queryMap["jgmc"] = etJgmc.trimText()
            if (cylyId.isNotBlank())
                queryMap["szd"] = cylyId
            if (jdlbId.isNotBlank())
                queryMap["jdlb"] = jdlbId
            if (zzdjId.isNotBlank())
                queryMap["zzdj"] = zzdjId
            if (etJdry.isNotBlack())
                queryMap["jdry"] = etJdry.trimText()
            RxBus.post(QueryMapEvent(queryMap = queryMap))
            finish()
        }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Cyly -> {
                    cylyId = it.dict.id
                    tvCyly.text = it.dict.name
                }
                Jdlb -> {
                    jdlbId = it.dict.id
                    tvJdlb.text = it.dict.name
                }
            }
        })
    }

    private var cylyId = ""

    private var jdlbId = ""

    private var zzdjId = ""

    override val layoutId = R.layout.act_jdjg_public_query

}