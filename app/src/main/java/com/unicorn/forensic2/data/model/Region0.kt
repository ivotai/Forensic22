package com.unicorn.forensic2.data.model

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

data class Region0(val dict: Dict) : AbstractExpandableItem<Region1>(), MultiItemEntity {

    override fun getLevel() = 0

    override fun getItemType() = 0

}