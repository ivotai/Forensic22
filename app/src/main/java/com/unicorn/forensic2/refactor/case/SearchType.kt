package com.unicorn.forensic2.refactor.case

enum class SearchType(
    val cn: String,
    val en: String,
    val hint: String
) {

    Ah("案号", "ah", "输入鉴定案号或案件案号"),
    Year("年度号", "year", "输入年份，比如2020"),
    Plaintiff("当事人", "plaintiff", "输入当事人姓名")
    ;

    companion object {
        val forCase get() = listOf(Ah, Year, Plaintiff)
    }

}

