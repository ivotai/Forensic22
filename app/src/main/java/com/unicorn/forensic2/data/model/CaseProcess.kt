package com.unicorn.forensic2.data.model

data class CaseProcess(
    val acceptDttm: Long,
    val action: String,
    val caseId: String,
    val handleDttm: Long,
    val handler: String,
    val handlerId: String,
    val idx: Int,
    val isLast: Int,
    val processId: String,
    val remark: String,
    val statusDb: Int
)