package com.unicorn.forensic2.data.model

import java.io.Serializable

data class JdLotteryTime(
    val caseId: String,
    val daysAppraisal: Int,
    val daysCheck: Int,
    val daysClearfee: Int,
    val daysEvidence: Int,
    val daysPay: Int,
    val lid: String?
): Serializable