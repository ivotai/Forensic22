package com.unicorn.forensic2.ui.adapter

import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.RxBus
import com.unicorn.forensic2.app.helper.TreeFetcher2
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.TreeNode2
import com.unicorn.forensic2.data.model.Fy
import com.unicorn.forensic2.ui.base.KVHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_text.*

class TreeNodeAdapter2() : BaseMultiItemQuickAdapter<MultiItemEntity, KVHolder>(ArrayList()) {

    lateinit var treeFetcher2: TreeFetcher2

    init {
        addItemType(0, R.layout.item_text)
    }

    override fun convert(helper: KVHolder, item: MultiItemEntity) {
        fun getSubItems(item: TreeNode2) {
            fun cope(items: List<Fy>) {
                Observable.fromIterable(items)
                    .map { TreeNode2(fy = it, nodeLevel = item.nodeLevel + 1) }
                    .toList()
                    .subscribeBy {
                        if (it.isEmpty())
                            RxBus.post(item)
                        else {
                            item.subItems = it
                            expand(helper.adapterPosition);
                        }
                    }
            }

            treeFetcher2.getChildren(item.fy.dm)
                .observeOnMain(mContext as LifecycleOwner)
                .subscribeBy(
                    onSuccess = {
                        cope(it)
                    },
                    onError = {
                    }
                )
        }

        item as TreeNode2
        helper.apply {
            root.text = item.fy.fymc
            val paddingStart = ConvertUtils.dp2px(16 * item.nodeLevel.toFloat())
            val paddingOther = ConvertUtils.dp2px(16f)
            root.setPadding(paddingStart, paddingOther, paddingOther, paddingOther)
        }
        helper.apply {
            root.safeClicks().subscribe {
                if (item.subItems == null || item.subItems.isEmpty())
                    getSubItems(item)
                else {
                    val pos = helper.adapterPosition
                    if (item.isExpanded) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                }
            }
        }
    }

}