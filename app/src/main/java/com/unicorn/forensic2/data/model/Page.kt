package com.unicorn.forensic2.data.model

data class Page<T>(
    val content: List<T>,
    val totalElements: String
)

