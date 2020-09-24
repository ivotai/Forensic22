package com.unicorn.forensic2.data.model

enum class Role(val roleTag: String, val cn: String) {

    Normal("Normal", "当事人"),
    JdjgAdmin("JdjgAdmin", "鉴定机构"),
    Pszj("Pszj", "评审专家"),
    Spry("Bafg", "审判人员"),
    Sfjd("Sfjd", "司技人员"),
    SfjdAdmin("SfjdAdmin", "司技人员")
    ;

    companion object {

        fun findByRoleTag(roleTag: String): Role {
            return values().find { roleTag == it.roleTag }!!
        }

        val all get() = listOf(Normal, JdjgAdmin, Pszj, Spry, Sfjd, SfjdAdmin)

        val roleTags = all.map { it.roleTag }
    }

}