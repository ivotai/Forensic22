package com.unicorn.forensic2.data.model

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

data class TreeNode(val dict: Dict, val nodeLevel: Int) : AbstractExpandableItem<TreeNode>(),
    MultiItemEntity {

    override fun getLevel() = nodeLevel

    override fun getItemType() = 0

}