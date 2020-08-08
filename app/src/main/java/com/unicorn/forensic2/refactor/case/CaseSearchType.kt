package com.unicorn.forensic2.refactor.case

enum class CaseSearchType(
    val cn: String
) {

    Ah("案号"),
    Year("年度号"),
    Plaintiff("当事人")
    ;

    companion object {
        val all get() = listOf(Ah, Year, Plaintiff)
    }

}

