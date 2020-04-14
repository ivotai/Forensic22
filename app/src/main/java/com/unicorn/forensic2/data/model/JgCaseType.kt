package com.unicorn.forensic2.data.model

enum class JgCaseType(val text: String) {
    ZBTZ("中标通知"),
    DJD("待鉴定"),
    YJD("已鉴定"),
    YJJ("已拒绝"),
    YXA("已销案")
    ;

    companion object {

        val all
            get() = listOf(ZBTZ, DJD, YJD, YJJ, YXA)

    }

}