package com.unicorn.forensic2.refactor.caseDemo

import java.io.Serializable

data class CaseDemo(
    val addDttm: Long,
    val addUser: String,
    val caseId: String,
    val content: String,
    val lastUpdateDate: Long,
    val mid: String
) : Serializable