package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.roleTag
import java.io.Serializable

enum class CaseType(val text: String) : Serializable {
    ZBTZ("中标通知"),
    DJD("待鉴定"),
    YJD("已鉴定"),
    YJJ("已拒绝"),
    YQX("已取消"),
    CLOSE("已结案"),
    UNCLOSE("未结案"),
    LASP("立案审批"),
    CYHSP("重摇号审批"),
    JASP("结案审批"),
    XASP("销案审批"),
    ;

    companion object {

        val all
            get() = when (roleTag) {
                Role.JdjgAdmin.en -> listOf(ZBTZ, DJD, YJD, YJJ, YQX)
                Role.Normal.en, Role.Sfjd.en, Role.Bafg.en -> listOf(CLOSE, UNCLOSE)
                Role.SfjdAdmin.en -> listOf(LASP, CYHSP, JASP, XASP)
                else -> listOf(CLOSE, UNCLOSE)
            }

        val default get() = all[0]

    }

}