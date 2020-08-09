package com.unicorn.forensic2.ui.my

import com.unicorn.forensic2.data.model.CaseType
import java.io.Serializable

enum class Backlog(
    val key: String,
    val caseType: CaseType? = null,
    val cn: String? = null,
    var count: Int = 0
) : Serializable {

    tztx("tztx", cn = "通知提醒"),
    dps("dps", cn = "待评审"),
    zbtz("zbtz", CaseType.ZBTZ),
    djd("djd", CaseType.DJD),
    yjd("yjd", CaseType.YJD),
    yjj("yjj", CaseType.YJJ),
    yqx("yqx", CaseType.YQX),
    yjaj("yjaj", CaseType.CLOSE),
    wjaj("wjaj", CaseType.UNCLOSE),
    lasp("lasp", CaseType.LASP),
    cyhsp("cyhsp", CaseType.CYHSP),
    jasp("jasp", CaseType.JASP),
    xasp("xasp", CaseType.XASP)
    ;

    companion object {
        fun findByKey(key: String): Backlog {
            return values().find { it.key == key }!!
        }
    }

    val cnX get() = caseType?.cn ?: cn

}