package com.unicorn.forensic2.refactor.caseDemo

data class AddCaseDemoParam(
    val caseId: String,
    val pid: String? = null,
    val content: String
)