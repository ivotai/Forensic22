package com.unicorn.forensic2.data.model

import androidx.annotation.DrawableRes
import com.unicorn.forensic2.R

enum class MyMenu(
    val text: String,
    @DrawableRes val imgRes: Int
) {

    ModifyPwd("修改密码", R.mipmap.modify_pwd),
    PersonInfo("个人信息", R.mipmap.personal_info),
    RegisterJdjg("注册机构", R.mipmap.register_jdjg),
    MyJdjg("我的机构", R.mipmap.zjxx),
    RegisterExpert("注册专家", R.mipmap.my_select),
    Logout("退出登录", R.mipmap.logout)
    ;

    companion object {
        val all
            get() = listOf(
                ModifyPwd, PersonInfo, RegisterJdjg,
                MyJdjg, RegisterExpert, Logout
            )
    }

}

