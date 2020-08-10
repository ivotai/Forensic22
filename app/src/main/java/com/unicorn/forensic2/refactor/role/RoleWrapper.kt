package com.unicorn.forensic2.refactor.role

import com.unicorn.forensic2.data.model.Role


enum class RoleWrapper(var roles: List<Role>) {

    Normal(listOf(Role.Normal)),
    JdjgAdmin(listOf(Role.JdjgAdmin)),
    Pszj(listOf(Role.Pszj)),
    Spry(listOf(Role.Spry)),
    SfjdMixture(listOf(Role.Sfjd, Role.SfjdAdmin))
    ;

    val cn: String get() = roles.first().cn

    val role: Role = roles.first()

    companion object {

        fun getRoleWrappers(roles: List<Role>): List<RoleWrapper> {
            val list = ArrayList<RoleWrapper>()
            if (Role.Normal in roles) list.add(Normal)
            if (Role.JdjgAdmin in roles) list.add(JdjgAdmin)
            if (Role.Pszj in roles) list.add(Pszj)
            if (Role.Spry in roles) list.add(Spry)
            when {
                Role.Sfjd in roles && Role.SfjdAdmin in roles -> {
                    SfjdMixture.roles = listOf(Role.Sfjd, Role.SfjdAdmin)
                    list.add(SfjdMixture)
                }
                Role.Sfjd in roles -> {
                    SfjdMixture.roles = listOf(Role.Sfjd)
                    list.add(SfjdMixture)
                }
                Role.SfjdAdmin in roles -> {
                    SfjdMixture.roles = listOf(Role.Sfjd)
                    list.add(SfjdMixture)
                }
            }
            return list
        }

    }

}