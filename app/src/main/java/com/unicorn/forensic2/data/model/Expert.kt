package com.unicorn.forensic2.data.model

data class Expert(
    val addr: String,
    val createdDate: Long,
    val email: String,
    val expertName: String,
    val fidphoto: Fidphoto,
    val fidphotoid: String,
    val lastUpdateDate: Long,
    val objectId: String,
    val phoneNumber: String,
    val sfzh: String,
    val status: Int,
    val userId: String,
    val zyms: String
)

data class Fidphoto(
    val attachedkey1: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
)