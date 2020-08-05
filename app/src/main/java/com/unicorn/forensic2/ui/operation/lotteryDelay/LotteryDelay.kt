package com.unicorn.forensic2.ui.operation.lotteryDelay

data class LotteryDelay(
    val applyDttm: Long,
    val applyState: Int,
    val caseId: String,
    val delayTo: Long,
    val did: String,
    val fee: Double,
    val fidjfqd: String,
    val fidsfbz: String,
    val jdryid: String,
    val jdryxm: String,
    val lastUpdateDate: Long,
    val lid: String,
    val terminate: Int,
    val approveBy: String,
    val approveDttm: Long,
    val approveInfo: String
) {
    companion object {
        val map = HashMap<Int, String>().apply {
            this[1] = "机构确认"
            this[0] = "新申请"
            this[2] = "已批准"
            this[3] = "已驳回"
        }
    }

    val applyStateStr: String get() = map[applyState] ?: ""

}
