package com.unicorn.forensic2.data.model

//data class UserLoginResult(
//    val cntNewlyToDo: Int,
//    val cntUpdatedMyCase: Int,
//    val identityChecked: Boolean,
//    val isAdmin: Boolean,
//    val sid: String,
//    val uid: String,
//    val userMenu: List<UserMenu>,
//    val userName: String
//)
data class LoginResult(
    val session: String,
    val success: Boolean,
    val user: User
)

data class User(
    val Admin: Boolean,
    val Bafg: Boolean,
    val Jdjg: Boolean,
    val JdjgAdmin: Boolean,
    val Normal: Boolean,
    val Pszj: Boolean,
    val Root: Boolean,
    val Sfjd: Boolean,
    val id: Long,
    val realName: Boolean,
    val roleTag: String,
    val roles: List<String>,
    val username: String
)

//data class UserMenu(
//    val namez: String,
//    val url: String
//) {
//
//    companion object {
//
//        val basicMenus
//            get() = listOf(
//                UserMenu(namez = "系统公告", url = "系统公告"),
//                UserMenu(namez = "图片新闻", url = "图片新闻"),
//                UserMenu(namez = "机构查询", url = "机构查询"),
//                UserMenu(namez = "摇号回放", url = "摇号回放"),
//                UserMenu(namez = "投诉建议", url = "投诉建议")
//            )
//
//    }
//
//}