package com.unicorn.forensic2.data

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.forensic2.data.model.Fy

data class TreeNode2(val fy: Fy, val nodeLevel: Int) : AbstractExpandableItem<TreeNode2>(),
    MultiItemEntity {

    override fun getLevel() = nodeLevel

    override fun getItemType() = 0

}