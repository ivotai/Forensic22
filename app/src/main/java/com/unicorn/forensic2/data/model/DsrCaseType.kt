package com.unicorn.forensic2.data.model

import java.io.Serializable

enum class DsrCaseType(val text: String) : Serializable {
    MYCASE("我的案件")
    ;

    companion object {

        val all
            get() = listOf(MYCASE)

    }

}