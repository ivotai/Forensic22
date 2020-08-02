package com.unicorn.forensic2.data.model

enum class Role(val en: String, val cn: String) {
    Normal("Normal", "当事人"),
    JdjgAdmin("JdjgAdmin", "鉴定机构"),
    Pszj("Pszj", "评审专家"),
    Bafg("Bafg", "办案法官"),
    Sfjd("Sfjd", "司技办案"),
    SfjdAdmin("SfjdAdmin", "司技主管")
    ;

    companion object {
        fun en2Cn(en: String): String {
            return values().find { en == it.en }?.cn ?: ""
        }
    }

}