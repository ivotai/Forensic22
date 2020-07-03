package com.unicorn.forensic2.data.model

import java.io.Serializable

data class UploadFile(
    val attachedkey1: String,
    val attachedkey2: String,
    val fileid: String,
    val filename: String,
    val filesavepath: String,
    val filetype: String,
    val uploadat: Long,
    val uploadby: String
): Serializable