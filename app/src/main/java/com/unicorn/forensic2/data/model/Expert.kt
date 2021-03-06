package com.unicorn.forensic2.data.model

import java.io.Serializable

data class Expert(
    var addr: String = "",
    val createdDate: Long = 0,
    var email: String = "",
    var expertName: String = "",
//    val fidphoto: Fidphoto,
    var fid_photo: String = "",
    val fidphotoid: String = "",
    val lastUpdateDate: Long = 0,
    val objectId: String = "",
    var phoneNumber: String = "",
    var sfzh: String = "",
    val status: Int = 0,
    val userId: String = "",
    var zyms: String = ""
) : Serializable

data class Fidphoto(
    val attachedkey1: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
) : Serializable