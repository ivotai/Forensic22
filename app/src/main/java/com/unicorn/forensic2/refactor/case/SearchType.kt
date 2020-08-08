package com.unicorn.forensic2.refactor.case

enum class SearchType(
    val cn: String
) {

    Ah("案号"),
    Year("年度号"),
    Plaintiff("当事人")
    ;

    companion object {
        val forCase get() = listOf(Ah, Year, Plaintiff)
    }

}

