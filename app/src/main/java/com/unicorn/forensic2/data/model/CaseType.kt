package com.unicorn.forensic2.data.model

import com.facebook.stetho.inspector.console.CLog
import com.unicorn.forensic2.app.roleTag
import java.io.Serializable

enum class CaseType(val text: String) : Serializable {
    ZBTZ("中标通知"),
    DJD("待鉴定"),
    YJD("已鉴定"),
    YJJ("已拒绝"),
    YXA("已销案"),

    CLOSE("已结案"),
    UNCLOSE("未结案")
    ;

    companion object {

        val all
            get() = when (roleTag) {
                Role.JdjgAdmin.en -> listOf(ZBTZ, DJD, YJD, YJJ, YXA)
                Role.Normal.en, Role.Sfjd.en, Role.Bafg.en -> listOf(CLOSE, UNCLOSE)
                Role.SfjdAdmin.en -> listOf()
                else -> listOf(CLOSE, UNCLOSE)
            }

        val default get() = all[0]

    }

}