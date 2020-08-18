package com.unicorn.forensic2.refactor.main.profile.backlog

import com.unicorn.forensic2.data.model.CaseType
import java.io.Serializable

enum class BacklogKey(
    val key: String,
    val caseType: CaseType? = null
) : Serializable {

    tztx("tztx"),
    dps("dps"),
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
        fun findByKey(key: String): BacklogKey {
            return values().find { it.key == key }!!
        }
    }

}