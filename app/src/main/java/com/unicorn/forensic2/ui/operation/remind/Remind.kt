package com.unicorn.forensic2.ui.operation.remind

import com.unicorn.forensic2.data.model.UploadFile

data class Remind(
    val caseId: String,
    val fileInfo: UploadFile?,
    val fileid: String,
    val fromFlag: Int,
    val jgid: String,
    val lastUpdateDate: Long,
    val remark: String,
    val remindDttm: Long,
    val acceptDttm:Long,
    val rid: String,
    val toFlag: Int
)

