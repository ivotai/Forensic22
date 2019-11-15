package com.unicorn.forensic2.app

import com.unicorn.forensic2.data.model.UserLoginResult

var isLogin = false

lateinit var userLoginResult: UserLoginResult

val sid :String get() =  userLoginResult.sid

val username get() =  userLoginResult.userName