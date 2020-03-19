package com.unicorn.forensic2.ui.adapter

import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.data.event.RefreshEvent
import com.unicorn.forensic2.data.model.Zjzz
import com.unicorn.forensic2.ui.base.KVHolder
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_zjzz.*

class ZjzzAdapter : BaseQuickAdapter<Zjzz, KVHolder>(R.layout.item_zjzz) {

    override fun convert(helper: KVHolder, item: Zjzz) {
        helper.apply {
            tvJdlb.text = item.jdlb
            tvZczyh.text = item.zczyh
            tvGrzc.text = item.grzc
            tvZyzsyxq.text = item.zyzsyxq.toDisplayFormat()
        }
        helper.apply {
            tvDelete.safeClicks().subscribe { showDeleteConfirm(item.objectId) }
        }
    }

    private fun showDeleteConfirm(objectId: String) {
        MaterialDialog(mContext).show {
            title(text = "确认删除？")
            positiveButton(text = "确认") { delete(objectId) }
        }
    }

    private fun delete(objectId: String) {
        ComponentHolder.appComponent.v1Api()
            .deleteZjzz(objectId = objectId)
            .observeOnMain(mContext as LifecycleOwner)
            .subscribeBy(
                onSuccess = {
                    if (!it.success) {
                        ToastUtils.showShort("删除失败")
                        return@subscribeBy
                    }
                    RxBus.post(RefreshEvent())
                },
                onError = {
                    ToastUtils.showShort("删除失败")
                }
            )
    }

}