package com.unicorn.forensic2.ui.operation.taskDoc

private val lid: String? = null
private val caseId: String? = null
private val TaskType //代码
        : Int? = null
private const val isSuccess = 0 //全部为0

private val remark //备注
        : String? = null

data class JdTaskDocParam(
    val lid: String,
    val caseId: String,
    val TaskType: Int,
    val isSuccess: Int = 0,
    val remark: String = ""
)