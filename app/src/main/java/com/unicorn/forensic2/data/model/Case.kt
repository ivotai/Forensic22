package com.unicorn.forensic2.data.model

import java.io.Serializable

//data class Case(
//    val fidzbtz: UploadFile?,
//    val ay: String,
//    val bafg: String,
//    val bafgId: String,
//    val caseId: String,
//    val caseNo: String,
//    val caseNode: String,
//    val caseNodeDb: Int,
//    val caseSource: String,
//    val caseStatus: String,
//    val caseStatusDb: Int,
//    val caseType: String,
//    val caseTypeDb: Int,
//    val closeTypeDisplay: String,
//    val courtAccept: String,
//    val courtApply: String,
//    val courtLxdhAccept: String,
//    val courtNameAccept: String,
//    val courtNameApply: String,
//    val dateApply: Long,
//    val dateLottery: Long,
//    val dateReply: Long,
//    val dateStart: Long,
//    val fee: Double,
//    val fidjdbg: String,
//    val fidjfqd: String,
//    val fidsfbz: String,
//    val fidyhhf: String,
//    val idx: String,
//    val isAgain: Boolean,
//    val isParty: Boolean,
//    val isTrial: Boolean,
//    val jdNo: String,
//    val jdlb: String,
//    val jdlb0: String,
//    val jdlbId: String,
//    val jdlbId0: Int,
//    val jdryid: String,
//    val jdryxm: String,
//    val jgid: String,
//    val jgmc: String,
//    val lid: String,
//    val lotteryStatus: String,
//    val lotteryStatusDb: Int,
//    val lotteryType: Int,
//    val operaPerson: String,
//    val plaintiff: String,
//    val planFinish: Long,
//    val reasonAppoint: String,
//    val remark: String,
//    val rollMode: Int,
//    val updateDttm: Long,
//    val zbr: String,
//    val zbrId: String
//)


data class Case(
    val jdNo: String,
    val caseId: String,
    val caseNo: String,
    val caseStatus: String,
    val dateAccept: Long,
    val dateClose: Long,
    val closeTypeDisplay: String
) : Serializable


