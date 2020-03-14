package com.unicorn.forensic2.data.model

data class Dict(
    val childList: List<Dict>,
    val id: String,
    val leaf: Int,
    val name: String
)