package com.unicorn.forensic2.data.model

import java.util.*

data class Case(
    val caseId: String,
    val caseNo: String,
    val caseStatus: String,
    val caseStatus2: String,
    val caseStatusDb: Int,
    val caseType: String,
    val caseTypeDb: Int,
    val isParty: Boolean,
    val isReexpertise: Boolean,
    val jafs: String,
    val jarq: Date,
    val jdNo: String,
    val jdlb: String,
    val jdlbId: String,
    val larq: Date,
    val lstCasePerson: List<LstCasePerson>,
    val lstCaseProcess: List<CaseProcess>
)

data class LstCasePerson(
    val caseId: String,
    val casePersonId: String,
    val name: String,
    val status: String,
    val statusFlag: Int
)

data class CaseProcess(
    val acceptAt: String,
    val caseId: String,
    val handledAt: String,
    val handledById: String,
    val handledByName: String,
    val idx: Int,
    val processId: String,
    val remark: String,
    val status: String,
    val statusDb: Int
)