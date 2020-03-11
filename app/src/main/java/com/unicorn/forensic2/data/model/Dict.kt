package com.unicorn.forensic2.data.model

data class Dict(
    val childList: List<Dict>,
    val id: Int,
    val leaf: Int,
    val name: String
)