package com.unicorn.forensic2.refactor.case.detail

enum class CaseDetailType(
    val cn: String
) {

    Basic("基本信息"),
    Jdxx("鉴定信息"),
    Process("案件流程")
    ;

    companion object {
        val all get() = listOf(Basic, Jdxx, Process)
    }

}

