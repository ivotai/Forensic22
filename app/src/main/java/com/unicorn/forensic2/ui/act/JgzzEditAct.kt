package com.unicorn.forensic2.ui.act

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.listItems
import com.blankj.utilcode.util.ToastUtils
import com.jakewharton.rxbinding3.widget.textChanges
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.data.event.JgzzMyListNeedRefreshEvent
import com.unicorn.forensic2.data.model.Jgzz
import com.unicorn.forensic2.data.model.TreeResult
import com.unicorn.forensic2.data.model.param.JgzzEditParam
import com.unicorn.forensic2.ui.base.BaseAct
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.act_jgzz_add.*

class JgzzEditAct : BaseAct() {

    private lateinit var jgzzEditParam: JgzzEditParam

    override fun initViews() {
        titleBar.setTitle("编辑资质详情")
    }

    override fun bindIntent() {
//        fun initJgzzParam()= with(jgzz){
//            jgzzEditParam = JgzzEditParam(
//                objectId = zzid,
//                jdlbId = jdlbId,
//
////            var zzdjId: Int = -1,
////            var cylyId: Int = -1,
////            var yxrq: String = "",
////            var spjg: String = "",
////            var zzsm: String = "",
////            var zzzh: String = "",
////            var fidzzzs_new: String = ""
//                )
//        }



        fun showZzdjDialog() {
            if (jgzzEditParam.jdlbId.isBlank()) {
                ToastUtils.showShort("请先选择鉴定类别")
                return
            }
            v1Api.getZzdj(jdlbId = jgzzEditParam.jdlbId)
                .observeOnMain(this)
                .subscribeBy(
                    onSuccess = {
                        MaterialDialog(this@JgzzEditAct).show {
                            listItems(items = it.map { it.name }) { _, index, _ ->
                                jgzzEditParam.zzdjId = it[index].id
                                this@JgzzEditAct.tvZzdj.text = it[index].name
                            }
                        }
                    },
                    onError = {
                        ToastUtils.showShort("获取资质等级失败")
                    }
                )
        }

        fun showDateDialog() {
            MaterialDialog(this).show {
                datePicker { _, date ->
                    jgzzEditParam.yxrq = date.time.time.toDisplayFormat()
                    this@JgzzEditAct.tvYxrq.text = jgzzEditParam.yxrq
                }
            }
        }

        fun nextStep() = with(jgzzEditParam) {
            if (jdlbId.isBlank()) {
                ToastUtils.showShort("请选择鉴定类别")
                return@with
            }
            if (zzdjId.isBlank()) {
                ToastUtils.showShort("请选择资质等级")
                return@with
            }
            if (cylyId.isBlank()) {
                ToastUtils.showShort("请选择机构所在地")
                return@with
            }
            if (yxrq.isBlank()) {
                ToastUtils.showShort("请选择有效日期")
                return@with
            }
            if (spjg.isBlank()) {
                ToastUtils.showShort("请输入审批机构")
                return@with
            }
            if (zzsm.isBlank()) {
                ToastUtils.showShort("请输入资质说明")
                return@with
            }
            if (zzzh.isBlank()) {
                ToastUtils.showShort("请输入资质证号")
                return@with
            }
            Intent(this@JgzzEditAct, JgzzAddPictureAct::class.java).apply {
                putExtra(JgzzAddParam, jgzzEditParam)
            }.let { startActivity(it) }
        }

        tvJdlb.safeClicks().subscribe { startAct(JdlbTreeAct::class.java) }
        tvZzdj.safeClicks().subscribe { showZzdjDialog() }
        tvCyly.safeClicks().subscribe { startAct(CylyTreeAct::class.java) }
        tvYxrq.safeClicks().subscribe { showDateDialog() }
        etSpjg.textChanges().map { it.toString() }.subscribe { jgzzEditParam.spjg = it }
        etZzsm.textChanges().map { it.toString() }.subscribe { jgzzEditParam.zzsm = it }
        etZzzh.textChanges().map { it.toString() }.subscribe { jgzzEditParam.zzzh = it }
        rtvNextStep.safeClicks().subscribe { nextStep() }
    }

    override fun registerEvent() {
        RxBus.registerEvent(this, TreeResult::class.java, Consumer {
            when (it.key) {
                Jdlb -> {
                    jgzzEditParam.jdlbId = it.dict.id
                    tvJdlb.text = it.dict.name
                }
                Cyly -> {
                    jgzzEditParam.cylyId = it.dict.id
                    tvCyly.text = it.dict.name
                }
            }
        })
        RxBus.registerEvent(this, JgzzMyListNeedRefreshEvent::class.java, Consumer {
            finish()
        })
    }

    private val jgzz by lazy { intent.getSerializableExtra(Param) as Jgzz }

    override val layoutId = R.layout.act_jgzz_add

}