package com.unicorn.forensic2.refactor.login

data class LoginEvent(
    val username: String,
    val password: String,
    val dm: String = "",
    val dmms: String = "",
    val isCourtLogin: Boolean = dm.isNotEmpty()
)