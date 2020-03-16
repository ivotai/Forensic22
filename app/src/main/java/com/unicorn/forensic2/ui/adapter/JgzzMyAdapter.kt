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
import com.unicorn.forensic2.data.model.Jgzz
import com.unicorn.forensic2.ui.act.JgzzEditAct
import com.unicorn.forensic2.ui.base.KVHolder
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_jgzz_my.*

class JgzzMyAdapter : BaseQuickAdapter<Jgzz, KVHolder>(R.layout.item_jgzz_my) {

    override fun convert(helper: KVHolder, item: Jgzz) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvCyly.text = item.cyly
            tvSpjg.text = item.spjg
            tvZzsm.text = item.zzsm

            tvZzdj.text = item.zzdj
            tvYxrq.text = item.yxrq.toDisplayFormat2()
            tvZzzh.text = item.zzzh
        }

        helper.apply {
            tvDelete.safeClicks().subscribe { showConfirmDialog(item) }
            tvEdit.safeClicks().subscribe {
                Intent(mContext, JgzzEditAct::class.java).apply {
                    putExtra(Param, item)
                }.let { mContext.startActivity(it) }
            }
        }
    }

    private fun showConfirmDialog(jgzz: Jgzz) {
        MaterialDialog(mContext).show {
            title(text = "确认删除该机构资质")
            positiveButton(text = "确认") { _ ->
                deleteJgzz(jgzz)
            }
        }
    }

    private fun deleteJgzz(jgzz: Jgzz) {
        ComponentHolder.appComponent.v1Api()
            .deleteJgzz(jgzzId = jgzz.zzid)
            .observeOnMain(mContext as LifecycleOwner)
            .subscribeBy(
                onSuccess = {
                    if (!it.success) {
                        ToastUtils.showShort("删除机构资质失败")
                        return@subscribeBy
                    }
                    RxBus.post(RefreshEvent())
                },
                onError = {
                    ToastUtils.showShort("删除机构资质失败")
                }
            )
    }

}