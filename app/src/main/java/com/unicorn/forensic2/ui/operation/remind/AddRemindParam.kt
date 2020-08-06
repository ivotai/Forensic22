package com.unicorn.forensic2.ui.operation.remind

data class AddRemindParam(
    val toFlag: Int = Remind.currentToFlag,
    val fromFlag: Int = Remind.currentFromFlag,
    val remark: String,
    val caseId: String,
    val jgId: String
)