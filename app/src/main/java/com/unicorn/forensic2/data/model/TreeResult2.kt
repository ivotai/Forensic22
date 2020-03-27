package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.data.TreeNode2

data class TreeResult2(
    val treeNode2: TreeNode2,
    val key :String
){
    val fy get() = treeNode2.fy
}