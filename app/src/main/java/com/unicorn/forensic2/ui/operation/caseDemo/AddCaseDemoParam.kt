package com.unicorn.forensic2.ui.operation.caseDemo

data class AddCaseDemoParam(
    val caseId: String,
    val pid: String? = null,
    val content: String
)