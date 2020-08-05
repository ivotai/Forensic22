package com.unicorn.forensic2.ui.jdxx

import com.unicorn.forensic2.data.model.UploadFile

data class Jdxx(
    val ay: String,
    val bafg: String,
    val bafgId: String,
    val caseId: String,
    val caseNo: String,
    val caseNode: String,
    val caseNodeDb: Int,
    val caseSource: String,
    val caseStatus: String,
    val caseStatusDb: Int,
    val caseType: String,
    val caseTypeDb: Int,
    val closeType: Int,
    val closeTypeDisplay: String,
    val courtAccept: String,
    val courtApply: String,
    val courtLxdhAccept: String,
    val courtNameAccept: String,
    val courtNameApply: String,
    val dateAccept: Long,
    val dateApply: Long,
    val dateClose: Long,
    val dateFinishRequest: Long,
    val dateLottery: Long,
    val dateRegister: Long,
    val dateReply: Long,
    val dateSetperson: Long,
    val dateStart: Long,
    val feeActual: Double,
    val fidyhhf: String?,
    val fidjdbg: String?,
    val fidzbtz: UploadFile?,
    val idx: String,
    val isAccept: Int,
    val isAgain: Boolean,
    val isParty: Boolean,
    val isTrial: Boolean,
    val jdNo: String,
    val jdlb: String,
    val jdlb0: String,
    val jdlbId: String,
    val jdlbId0: Int,
    val jgid: String,
    val jgmc: String,
    val lid: String,
    val lotteryStatusDb: Int,
    val lotteryType: Int,
    val operaPerson: String,
    val plaintiff: String,
    val reasonAppoint: String,
    val rollMode: Int,
    val updateDttm: Long,
    val zbr: String,
    val zbrId: String
) {

    companion object {
        val map = HashMap<Int, String>().apply {
            this[0] = "待预约"
            this[5] = "已预约"
            this[10] = "已摇号"
            this[20] = "鉴定中"
            this[21] = "委托书盖章申请"
            this[22] = "委托书已盖章"
            this[24] = "报告退回"
            this[25] = "报告已提交"
            this[28] = "报告已审核 "
            this[30] = "已完成"
            this[31] = "已拒绝"
            this[32] = "已取消"
        }
    }

    val lotteryStatusStr: String get() = map[lotteryStatusDb] ?: ""

}

