package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.toDisplayFormat
import com.unicorn.forensic2.app.zjlxList
import com.unicorn.forensic2.data.model.Jdry
import com.unicorn.forensic2.ui.base.KVHolder
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

//        helper.apply {
//            tvDelete.safeClicks().subscribe { showConfirmDialog(item) }
//            tvEdit.safeClicks().subscribe {
//                Intent(mContext, JgzzEditAct::class.java).apply {
//                    putExtra(Param, item)
//                }.let { mContext.startActivity(it) }
//            }
//        }
    }

//    private fun showConfirmDialog(jgzz: Jgzz) {
//        MaterialDialog(mContext).show {
//            title(text = "确认删除该机构资质")
//            positiveButton(text = "确认") { _ ->
//                deleteJgzz(jgzz)
//            }
//        }
//    }
//
//    private fun deleteJgzz(jgzz: Jgzz) {
//        ComponentHolder.appComponent.v1Api()
//            .deleteJgzz(jgzzId = jgzz.zzid)
//            .observeOnMain(mContext as LifecycleOwner)
//            .subscribeBy(
//                onSuccess = {
//                    if (!it.success) {
//                        ToastUtils.showShort("删除机构资质失败")
//                        return@subscribeBy
//                    }
//                    RxBus.post(RefreshEvent())
//                },
//                onError = {
//                    ToastUtils.showShort("删除机构资质失败")
//                }
//            )
//    }

}