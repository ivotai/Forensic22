package com.unicorn.forensic2.data.model

enum class MyMenu(val text: String) {

    ModifyPwd("修改密码"),
    PersonInfo("个人信息"),
    RegisterJdjg("注册机构"),
    MyJdjg("我的机构"),
    RegisterExpert("注册专家"),
    Logout("退出登录")
    ;

    companion object {
        val all
            get() = listOf(
                ModifyPwd, PersonInfo, RegisterJdjg,
                MyJdjg, RegisterExpert, Logout
            )
    }

}

