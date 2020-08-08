package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.roleTag
import java.io.Serializable

enum class CaseType(val cn: String, val key: String) : Serializable {

    TZTX("通知提醒", "tztx"),
    DPS("待评审", "dps"),
    ZBTZ("中标通知", "zbtz"),
    DJD("待鉴定", "djd"),
    YJD("已鉴定", "yjd"),
    YJJ("已拒绝", "yjj"),
    YQX("已取消", "yqx"),
    CLOSE("已结案", "yjaj"),
    UNCLOSE("未结案", "wjaj"),
    LASP("立案审批", "lasp"),
    CYHSP("重摇号审批", "cyhsp"),
    JASP("结案审批", "jasp"),
    XASP("销案审批", "xasp")
    ;

    companion object {

        val all
            get() = when (roleTag) {
                Role.JdjgAdmin.en -> listOf(ZBTZ, DJD, YJD, YJJ, YQX)
                Role.Normal.en, Role.Sfjd.en, Role.Spry.en -> listOf(CLOSE, UNCLOSE)
                Role.SfjdAdmin.en -> listOf(
                    LASP,
                   CYHSP,
                    JASP,
                   XASP
                )
                else -> listOf(CLOSE, UNCLOSE)
            }

        val default get() = all[0]


        fun findByKey(key: String): CaseType {
            return values().find { it.key == key }!!
        }
    }

}