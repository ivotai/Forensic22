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
    val statusDb: Int?
) : Serializable {

    companion object {
        val map = HashMap<Int, String>().apply {
            this[-1] = "未知"
            this[10] = "新申请"
            this[11] = "退回初审"
            this[15] = "退回审判"
            this[20] = "待确认"
            this[30] = "待分案"
            this[50] = "案件办理"
            this[51] = "审批未通过"
            this[55] = "报告已提交"
            this[56] = "报告已审核"
            this[57] = "报告已退回"
            this[60] = "结案审批"
            this[62] = "销案审批"
            this[70] = "已结案"
            this[71] = "无法鉴定"
            this[80] = "已销案"
        }
    }
    
    val caseStatusX get() = caseStatus ?: jdlbDisplay1 ?: jdlbDisplay2 ?: ""

    val caseTypeX: String
        get() {
            return if (statusDb == null) caseType else map[statusDb]!!
        }

}


