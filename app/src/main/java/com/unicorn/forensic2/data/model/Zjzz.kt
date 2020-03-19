package com.unicorn.forensic2.data.model

import java.io.Serializable

data class Zjzz(
    val createdDate: Long,
    val eid: String,
    val fidzczs: Fidzczs,
    val fidzczsid: String,
    var fid_zczs:String = "",
    val fidzyzs: Fidzyzs,
    val fidzyzsid: String,
    var fid_zyzs:String = "",
    val grzc: String,
    val jdlb: String,
    val jdlbId: String,
    val lastUpdateDate: Long,
    val objectId: String,
    val status: Int,
    val zczyh: String,
    val zyzsyxq: Long
): Serializable

data class Fidzczs(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
): Serializable

data class Fidzyzs(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
): Serializable