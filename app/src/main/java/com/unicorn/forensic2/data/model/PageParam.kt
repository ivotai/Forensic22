package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.defaultPageSize

data class PageParam<T>(
    val pageNo: Int = 1,
    val pageSize: Int = defaultPageSize,
    val searchParam: T
)