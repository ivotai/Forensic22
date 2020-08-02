package com.unicorn.forensic2.app

import com.unicorn.forensic2.data.model.LoginResult
import com.unicorn.forensic2.data.model.User

var isLogin = false

lateinit var loginResult: LoginResult

val session: String get() = loginResult.session
val user: User get() = loginResult.user

val roleTag: String
    get() {
        return if (!isLogin) "none" else user.roleTag
    }

//val sid: String get() = loginResult.sid

//val username get() = loginResult.userName

//val identityChecked get() = loginResult.identityChecked

//val userMenu get() = loginResult.userMenu