package com.unicorn.forensic2.data

import java.io.Serializable

data class DsrCase(
    val ay: String,
    val caseId: String,
    val caseNo: String,
    val caseType: Int,
    val closeTypeDisplay: String,
    val courtAccept: String,
    val courtApply: String,
    val courtNameAccept: String,
    val courtNameApply: String,
    val dateAccept: Long,
    val dateApply: Long,
    val dateRegister: Long,
    val dateSetperson: Long,
    val isAgain: Int,
    val isParty: Int,
    val isTrial: Int,
    val jdNo: String,
    val plaintiff: String,
    val statusDb: Int,
    val statusFlag: Int,
    val updateDttm: Long,
    val workDays: Int,
    val workDaysW: Int
) : Serializable {
    val caseTypeString
        get() = when (caseType) {
            1 -> "鉴定委托"
            2 -> "保外就医审核"
            3 -> "技术咨询"
            4 -> "技术审核"
            5 -> "诉前鉴定"
            else -> "其他"
        }
}
