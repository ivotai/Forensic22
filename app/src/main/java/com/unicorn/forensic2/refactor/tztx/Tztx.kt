package com.unicorn.forensic2.refactor.tztx

data class Tztx(
    val id: String,
    val mphone: String,
    val nid: String,
    val notification: Notification,
    val readtime: Long,
    val sendtime: Long,
    val uid: String
)

data class Notification(
    val addtime: Long,
    val adduserid: String,
    val attachedkey: String,
    val caseNo: String,
    val msg: String,
    val nid: String,
    val notifytype: Int
) {

    val notifytypeStr get() = map[notifytype]

    companion object {
        val map = HashMap<Int, String>().apply {
            this[1] = "机构提醒"
            this[2] = "当事人提醒"
            this[3] = "摇号提醒"
            this[4] = "办案提醒"
        }
    }

}