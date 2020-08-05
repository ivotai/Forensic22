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
    val caseStatus: String?,
    val dateAccept: Long,
    val dateClose: Long,
    val closeTypeDisplay: String,
    val courtNameApply: String,
    val caseType: String,
    val courtNameAccept: String,
    val lid: String?,
    val jgid: String,
    val jdlbId: String,
    val jdlbDisplay1: String?,
    val jdlbDisplay2: String?,
    val statusDb: Int?,
    val statusFlag: Int?
) : Serializable {

    companion object {
        val map = HashMap<Int, String>().apply {
            this[1] = "鉴定委托"
            this[2] = "保外就医审核"
            this[3] = "技术咨询"
            this[4] = "技术审核"
            this[5] = "诉前鉴定"
            this[9] = "其他"
        }

        val map2 = HashMap<Int, String>().apply {
            this[1] = "内勤初审"
            this[2] = "立案审批"
            this[3] = "待分案"
            this[4] = "摇号"
            this[5] = "案件办理"
            this[6] = "结案审批"
            this[7] = "已结案"
            this[8] = "已销案"
        }
    }

    val caseStatusX: String?
        get() {
            return if (statusFlag == null) caseStatus else map2[statusFlag]
        }

    val caseTypeX: String
        get() {
            val char = caseType[0]
            return if (char.isDigit()) map[Character.getNumericValue(char)] ?: ""
            else caseType
        }

    val lidX: String
        get() = lid ?: caseId

}


