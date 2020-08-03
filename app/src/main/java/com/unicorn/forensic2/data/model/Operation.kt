package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.roleTag
import com.unicorn.forensic2.ui.fra.CaseListFra

enum class Operation(val cn: String) {
    // 受理不受理等于一个回复
    SL("受理"),
    BSL("不受理"),
    AJBW("案件备忘"),   // 暂时不做
    BATX("办案提醒"),   // 暂时不做
    JDFK("鉴定反馈"),
    BGPF("变更批复"),
    JDBG("鉴定报告"),
    TYAL("同意立案"),
    THCS("退回初审"),
    TYCYH("同意重摇号"),
    BHCYH("驳回重摇号"),
    TYJA("同意结案"),
    BHJA("驳回结案"),
    TYXA("同意销案"),
    BHXA("驳回销案"),
    ;

    companion object {
        val all
            get():List<Operation> {
                val caseType = CaseListFra.caseType
                return when (roleTag) {
                    Role.JdjgAdmin.en -> when (caseType) {
                        CaseType.ZBTZ -> listOf(SL, BSL)
                        CaseType.DJD -> listOf(AJBW, BATX, JDFK, BGPF, JDBG)
                        CaseType.YJD -> listOf(AJBW)
                        else -> listOf()
                    }
                    Role.Sfjd.en -> when (caseType) {
                        CaseType.UNCLOSE -> listOf(AJBW, BATX, BGPF)
                        CaseType.CLOSE -> listOf(AJBW)
                        else -> listOf()
                    }
                    Role.SfjdAdmin.en -> when (caseType) {
                        CaseType.LASP -> listOf(TYAL, THCS)
                        CaseType.CYHSP -> listOf(TYCYH, BHCYH)
                        CaseType.JASP -> listOf(TYJA, BHJA)
                        CaseType.XASP -> listOf(TYXA, BHXA)
                        else -> listOf()
                    }
                    Role.Bafg.en -> when (caseType) {
                        CaseType.UNCLOSE -> listOf(AJBW, BATX)
                        CaseType.CLOSE -> listOf(AJBW)
                        else -> listOf()
                    }
                    else -> listOf()
                }
            }
    }

}