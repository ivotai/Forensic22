package com.unicorn.forensic2.ui.my

import com.unicorn.forensic2.data.model.CaseType

data class Backlog(
    val caseType: CaseType,
    val count: Int
)