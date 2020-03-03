package com.unicorn.forensic2.data.model

enum class CaseType(val namez: String) {
    DjdList("机构"),
    DpsList("专家"),
    MyCase("当事人");

    companion object {
        val all get() = listOf(DjdList, DpsList, MyCase)
    }
}