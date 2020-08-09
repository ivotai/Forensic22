package com.unicorn.forensic2.refactor.case

enum class SearchType(
    val cn: String,
    val en: String
) {

    Ah("鉴定案号", "ah"),
    Year("年度号", "year"),
    Plaintiff("当事人", "plaintiff")
    ;

    companion object {
        val forCase get() = listOf(Ah, Year, Plaintiff)
    }

}

