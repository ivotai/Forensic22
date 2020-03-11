package com.unicorn.forensic2.data.model

import com.chad.library.adapter.base.entity.MultiItemEntity

data class Region1(val dict: Dict) : MultiItemEntity {

    override fun getItemType() = 1

}