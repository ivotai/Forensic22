package com.unicorn.forensic2.data.model

data class UserLoginResult(
    val cntNewlyToDo: Int,
    val cntUpdatedMyCase: Int,
    val identityChecked: Boolean,
    val isAdmin: Boolean,
    val sid: String,
    val uid: String,
    val userMenu: List<UserMenu>,
    val userName: String
)

data class UserMenu(
    val name: String,
    val url: String
) {

    companion object {

        val basicMenus
            get() = listOf(
                UserMenu(name = "系统公告", url = "系统公告"),
                UserMenu(name = "图片新闻", url = "图片新闻"),
                UserMenu(name = "机构查询", url = "机构查询"),
                UserMenu(name = "摇号回放", url = "摇号回放"),
                UserMenu(name = "投诉建议", url = "投诉建议")
            )

    }

}