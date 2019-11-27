package com.unicorn.forensic2.data.model

import com.blankj.utilcode.util.ToastUtils

class PageResponse<T>(
    errorCode: String,
    var success: Boolean,
    var errorMsg: String,
    val items: List<T>,
    val pageNo: Int,
    val pageSize: Int,
    val total: Int,
    val totalPage: Int
) : ErrorCode(errorCode) {

    val failed: Boolean
        get() {
            if (!success) ToastUtils.showShort(errorMsg)
            return !success
        }

}