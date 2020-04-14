package com.unicorn.forensic2.data.model

enum class ZjCaseType(val text: String) {
    DPS("待评审"),
    YPS("已评审")
    ;

    companion object {

        val all
            get() = listOf(
                DPS,
                YPS
            )

    }

}