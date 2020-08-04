package com.unicorn.forensic2.ui.operation.hf

data class ReplyParam(
    val lid: String,        // lotteryID
    val isAccept: Int,  // int,1:接受,0:不接受
    val rejectInfo: String  // 拒绝原因,拒绝时才填写
)
