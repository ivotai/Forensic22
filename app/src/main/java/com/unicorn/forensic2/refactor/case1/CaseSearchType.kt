package com.unicorn.forensic2.refactor.case1

enum class CaseSearchType(
    val cn: String,
    val en: String,
    val hint: String
) {

    Ah("案号", "ah", "鉴定案号或案件案号"),
    Year("年度号", "year", "2020"),
    Plaintiff("当事人", "plaintiff", "当事人姓名")
    ;

    companion object {
        val all get() = listOf(Ah, Year, Plaintiff)
    }

}

