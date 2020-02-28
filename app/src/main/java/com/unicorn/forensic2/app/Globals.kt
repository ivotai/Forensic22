package com.unicorn.forensic2.app

import com.unicorn.forensic2.data.model.LoginResult

var isLogin = false

lateinit var loginResult: LoginResult

val session :String get()= loginResult.session

//val userId: String get() = loginResult.uid

//val sid: String get() = loginResult.sid

//val username get() = loginResult.userName

//val identityChecked get() = loginResult.identityChecked

//val userMenu get() = loginResult.userMenu