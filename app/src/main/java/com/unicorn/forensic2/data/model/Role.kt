package com.unicorn.forensic2.data.model

enum class Role(val en: String, val cn: String) {

    Normal("Normal", "当事人"),
    JdjgAdmin("JdjgAdmin", "鉴定机构"),
    Pszj("Pszj", "评审专家"),
    Spry("Spry", "审判人员"),
    Sfjd("Sfjd", "司技人员"),
    SfjdAdmin("SfjdAdmin", "司技人员")
    ;

    companion object {
        fun en2Cn(en: String): String {
            return values().find { en == it.en }?.cn ?: ""
        }
    }

}