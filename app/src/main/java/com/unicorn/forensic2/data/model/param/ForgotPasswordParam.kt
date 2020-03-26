package com.unicorn.forensic2.data.model.param

data class ForgotPasswordParam(
    val phoneNo: String,
    val password: String,
    val verifyCode: String
)