package com.unicorn.forensic2.ui.operation.taskDoc

data class JdTaskDocParam(
    val lid: String?,
    val caseId: String,
    val taskType: Int,
    val isSuccess: Int = 0,
    val remark: String = ""
)