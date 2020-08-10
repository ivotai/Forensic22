package com.unicorn.forensic2.refactor.role

import com.unicorn.forensic2.data.model.Role

enum class RoleWrapper(var role: Role) {

    Normal(Role.Normal),
    JdjgAdmin(Role.JdjgAdmin),
    Pszj(Role.Pszj),
    Spry(Role.Spry),
    SfjdMixture(Role.Sfjd)
    ;

    //

}