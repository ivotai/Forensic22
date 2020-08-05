package com.unicorn.forensic2.ui.operation.tztx

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
    val msg: String,
    val nid: String,
    val notifytype: Int
)