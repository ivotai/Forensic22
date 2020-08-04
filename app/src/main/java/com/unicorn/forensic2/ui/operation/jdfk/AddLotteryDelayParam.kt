package com.unicorn.forensic2.ui.operation.jdfk

data class AddLotteryDelayParam(
    val lid: String,
    val fee: Int,
    val delayTo: String,
    val jdryid: List<String>,
    val jdryxm: List<String>,
    val terminate: Int
)