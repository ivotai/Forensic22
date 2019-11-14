package com.unicorn.forensic2.data.model

import com.blankj.utilcode.util.ToastUtils

class Response<T>(
    private val success: Boolean,
    private val errorMsg: String,
    errorCode: String,
    val data: T
) : ErrorCode(errorCode) {
    val failed: Boolean
        get() {
            if (!success) ToastUtils.showShort(errorMsg)
            return !success
        }
}