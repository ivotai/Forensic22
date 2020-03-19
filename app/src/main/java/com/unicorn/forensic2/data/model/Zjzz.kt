package com.unicorn.forensic2.data.model

data class Zjzz(
    val createdDate: Long,
    val eid: String,
    val fidzczs: Fidzczs,
    val fidzczsid: String,
    val fidzyzs: Fidzyzs,
    val fidzyzsid: String,
    val grzc: String,
    val jdlb: String,
    val jdlbId: String,
    val lastUpdateDate: Long,
    val objectId: String,
    val status: Int,
    val zczyh: String,
    val zyzsyxq: Long
)

data class Fidzczs(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
)

data class Fidzyzs(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
)