package com.unicorn.forensic2.ui.adapter

import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.observeOnMain
import com.unicorn.forensic2.app.safeClicks
import com.unicorn.forensic2.data.model.Dict
import com.unicorn.forensic2.data.model.Region0
import com.unicorn.forensic2.data.model.Region1
import com.unicorn.forensic2.ui.base.KVHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_text0.*

class RegionAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, KVHolder>(ArrayList()) {

    init {
        addItemType(0, R.layout.item_text0)
        addItemType(1, R.layout.item_text1)
    }

    override fun convert(helper: KVHolder, item: MultiItemEntity) {
        fun getRegion1(item: Region0) {
            fun cope(dicts: List<Dict>) {
                Observable.fromIterable(dicts)
                    .map { Region1(dict = it) }
                    .toList()
                    .subscribeBy {
                        item.subItems = it
                        expand(helper.adapterPosition);
                    }
            }

            val v1Api = ComponentHolder.appComponent.v1Api()
            v1Api.getRegion(item.dict.id)
                .observeOnMain(mContext as LifecycleOwner)
                .subscribeBy(
                    onSuccess = {
                        cope(it)
                    },
                    onError = {
                        ToastUtils.showShort("获取机构所在地失败")
                    }
                )
        }

        when (item.itemType) {
            0 -> {
                item as Region0
                helper.apply {
                    root.text = item.dict.name
                }
                helper.apply {
                    root.safeClicks().subscribe {
                        if (item.subItems == null || item.subItems.isEmpty())
                            getRegion1(item)
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
            1 -> {
                item as Region1
                helper.apply {
                    root.text = item.dict.name
                }
            }
        }
    }

}