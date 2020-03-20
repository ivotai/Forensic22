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
) {
    val status: String
        get() = when (statusDb) {
            10, 11, 15 -> "内勤初审"
            20 -> "领导确认"
            30 -> "分案"
            40, 41, 45 -> "摇号"
            50, 51, 55, 56, 57, 62 -> "案件办理"
            60 -> "结案审批"
            70 -> "已结案"
            71 -> "结案审批"
            80 -> "销案"
            else -> ""
        }
}