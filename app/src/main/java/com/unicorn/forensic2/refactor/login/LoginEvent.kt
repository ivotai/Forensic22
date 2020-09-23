package com.unicorn.forensic2.refactor.login

data class LoginEvent(
    val username: String,
    val password: String,
    val court: String? = null
)