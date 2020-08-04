package com.unicorn.forensic2.ui.operation.taskDoc

data class JdTaskDocParam(
    val lid: String,
    val caseId: String,
    val TaskType: Int,
    val isSuccess: Int = 0,
    val remark: String = ""
)