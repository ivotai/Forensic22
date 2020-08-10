package com.unicorn.forensic2.data.model

data class LoginResult(
    val session: String,
    val success: Boolean,
    var user: User
)

data class User(
    val Admin: Boolean,
    val Bafg: Boolean,
    val Jdjg: Boolean,
    var JdjgAdmin: Boolean,
    var Normal: Boolean,
    var Pszj: Boolean,
    val Root: Boolean,
    val Sfjd: Boolean,
    val id: Long,
    val expertId:Long,
    val realName: Boolean,  // 1已认证 2未认证
    val roles: List<String>,
    val username: String
)

data class PersonalInfoResponse(
    val success: Boolean,
    val user: User
)