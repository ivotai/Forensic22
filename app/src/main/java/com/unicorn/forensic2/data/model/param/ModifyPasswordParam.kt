package com.unicorn.forensic2.data.model.param

data class ModifyPasswordParam(
    val originPassword: String,
    val newPassword: String
)