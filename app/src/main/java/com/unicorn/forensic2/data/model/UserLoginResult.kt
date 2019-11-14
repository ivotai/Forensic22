package com.unicorn.forensic2.data.model

data class UserLoginResult(
    val cntNewlyToDo: Int,
    val cntUpdatedMyCase: Int,
    val identityChecked: Boolean,
    val isAdmin: Boolean,
    val sid: String,
    val uid: String,
    val userName: String
)