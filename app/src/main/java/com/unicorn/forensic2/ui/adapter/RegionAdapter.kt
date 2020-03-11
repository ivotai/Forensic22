package com.unicorn.forensic2.ui.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.R
import com.unicorn.forensic2.data.model.Region0
import com.unicorn.forensic2.data.model.Region1
import com.unicorn.forensic2.ui.base.KVHolder
import kotlinx.android.synthetic.main.item_text.*

class RegionAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, KVHolder>(ArrayList()) {

    init {
        addItemType(0, R.layout.item_text)
        addItemType(1, R.layout.item_text)
    }

    override fun convert(helper: KVHolder, item: MultiItemEntity) {
        when (item.itemType) {
            0 -> {
                item as Region0
                helper.apply {
                    root.text = item.dict.name
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