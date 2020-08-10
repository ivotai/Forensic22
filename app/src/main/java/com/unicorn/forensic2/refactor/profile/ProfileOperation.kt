package com.unicorn.forensic2.refactor.profile

import com.mikepenz.iconics.typeface.IIcon
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.icon.Light

enum class ProfileOperation(
    val text: String,
    val iIcon: IIcon
) {

    ModifyPwd("修改密码", Light.Icon.light_lock),
    PersonInfo("个人信息", Light.Icon.light_user_edit),
    SwitchRole("切换角色", Light.Icon.light_repeat),
    RegisterJdjg("注册机构", Light.Icon.light_building),
    MyJdjg("我的机构", Light.Icon.light_university),
    RegisterExpert("注册专家", Light.Icon.light_user_tie),
    Logout("退出登录", Light.Icon.light_sign_out_alt)

    ;

    companion object {

        val all
            get() =
                when (role) {
                    Role.Spry, Role.Sfjd, Role.SfjdAdmin -> listOf(ModifyPwd, SwitchRole, Logout)
                    else -> listOf(
                        ModifyPwd,
                        PersonInfo,
                        SwitchRole,
                        RegisterJdjg,
                        MyJdjg,
                        RegisterExpert,
                        Logout
                    )
                }

    }

}

