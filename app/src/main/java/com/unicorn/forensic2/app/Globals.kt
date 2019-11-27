package com.unicorn.forensic2.app

import com.unicorn.forensic2.data.model.UserLoginResult

var isLogin = false

lateinit var userLoginResult: UserLoginResult


val userId: String get() = userLoginResult.uid

val sid: String get() = userLoginResult.sid

val username get() = userLoginResult.userName

val identityChecked get() = userLoginResult.identityChecked

val userMenu get() = userLoginResult.userMenu