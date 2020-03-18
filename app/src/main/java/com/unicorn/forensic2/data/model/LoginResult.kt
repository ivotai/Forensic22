package com.unicorn.forensic2.data.model

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
    val expertId:String,
    val realName: Boolean,
    val roleTag: String,
    val roles: List<String>,
    val username: String
)
