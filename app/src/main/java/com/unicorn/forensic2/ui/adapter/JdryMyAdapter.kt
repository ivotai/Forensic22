package com.unicorn.forensic2.ui.adapter

import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.*
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Jdry
import com.unicorn.forensic2.ui.act.addOrEdit.JdryMyEditAct
import com.unicorn.forensic2.ui.base.KVHolder
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_jdry_my.*

class JdryMyAdapter : BaseQuickAdapter<Jdry, KVHolder>(R.layout.item_jdry_my) {

    override fun convert(helper: KVHolder, item: Jdry) {
        helper.apply {
            tvXm.text = item.xm
            tvZjhm.text = item.zjhm
            tvZczyh.text = item.zczyh
            tvZyzsyxq.text = item.zyzsyxq.toDisplayFormat()
            tvZjlx.text = zjlxList.find { it.id == item.zjlx }?.name
            tvGrzc.text = item.grzc
            tvJdlb.text = item.jdlb
            tvMphone.text = item.mphone
        }

        helper.apply {
            tvDelete.safeClicks().subscribe { showConfirmDialog(item.jdryid) }
            tvEdit.safeClicks().subscribe {
                Intent(mContext, JdryMyEditAct::class.java).apply {
                    putExtra(Param, item)
                }.let { mContext.startActivity(it) }
            }
        }
    }

    private fun showConfirmDialog(objectId: String) {
        MaterialDialog(mContext).show {
            title(text = "确认删除该鉴定人员")
            positiveButton(text = "确认") { _ ->
                delete(objectId)
            }
        }
    }

    private fun delete(objectId: String) {
        ComponentHolder.appComponent.v1Api()
            .deleteJdry(objectId = objectId)
            .observeOnMain(mContext as LifecycleOwner)
            .subscribeBy(
                onSuccess = {
                    if (!it.success) {
                        ToastUtils.showShort("删除鉴定人员失败")
                        return@subscribeBy
                    }
                    RxBus.post(RefreshEvent())
                },
                onError = {
                    ToastUtils.showShort("删除鉴定人员失败")
                }
            )
    }

}