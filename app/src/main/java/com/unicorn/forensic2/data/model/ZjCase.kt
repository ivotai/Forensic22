package com.unicorn.forensic2.data.model

data class ZjCase(
    val createdDate: Long,
    val eid: String,
    val lastUpdateDate: Long,
    val objectId: String,
    val review: Review,
    val rid: String
)

data class Review(
    val beginDate: Long,
    val endDate: Long,
    val fidreview: String,
    val fileName: String,
    val lastUpdateDate: Long,
    val objectId: String,
    val remark: String,
    val status: Int
)