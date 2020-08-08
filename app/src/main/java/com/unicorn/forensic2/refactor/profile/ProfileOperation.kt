package com.unicorn.forensic2.refactor.profile

import com.mikepenz.iconics.typeface.IIcon
import com.unicorn.forensic2.app.user
import com.unicorn.forensic2.refactor.icon.Light

enum class ProfileOperation(
    val text: String,
    val iIcon: IIcon
) {

    ModifyPwd("修改密码", Light.Icon.light_lock),
    PersonInfo("个人信息", Light.Icon.light_user_edit),
    RegisterJdjg("注册机构", Light.Icon.light_building),
    MyJdjg("我的机构", Light.Icon.light_university),
    RegisterExpert("注册专家", Light.Icon.light_user_tie),
    Logout("退出登录", Light.Icon.light_sign_out_alt),
    SwitchRole("切换角色", Light.Icon.light_repeat)
    ;

    companion object {
        val all
            get():List<ProfileOperation> {
                return ArrayList<ProfileOperation>().apply {
                    addAll(listOf(ModifyPwd, PersonInfo))
                    if (user.roles.size > 1) {
                        add(SwitchRole)
                    }
                    addAll(listOf(MyJdjg, RegisterJdjg, RegisterExpert, Logout))
                }
            }
    }

}

