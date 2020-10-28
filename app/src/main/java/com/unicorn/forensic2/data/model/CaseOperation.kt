package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.role

enum class CaseOperation(val cn: String) {
    HF("回复"),             // 受理不受理
    AJBW("案件备忘"),       // 暂时不做
    BATX("办案提醒"),       // 暂时不做
    JDFK("鉴定反馈"),
    BGPF_JDJGADMIN("变更批复"),
    JDBG("鉴定报告"),
    JDFY("鉴定费用"),
    BGPF_SFJD("变更批复"),
    LASP("立案审批"),
    CYHSP("重摇要审批"),
    JASP("结案审批"),
    XASP("销案审批")
    ;

    companion object {
        fun all(caseType: CaseType) = when (role) {
            Role.JdjgAdmin -> when (caseType) {
                CaseType.ZBTZ -> listOf(HF)
                CaseType.DJD -> listOf(AJBW, BATX, JDFK, BGPF_JDJGADMIN, JDFY, JDBG)
                CaseType.YJD -> listOf(AJBW, JDFY, JDBG)
                else -> listOf()
            }
            Role.Sfjd -> when (caseType) {
                CaseType.UNCLOSE -> listOf(AJBW, BATX, BGPF_SFJD)
                CaseType.CLOSE -> listOf(AJBW)
                else -> listOf()
            }
            Role.SfjdAdmin -> when (caseType) {
                CaseType.LASP -> listOf(LASP)
                CaseType.CYHSP -> listOf(CYHSP)
                CaseType.JASP -> listOf(JASP)
                CaseType.XASP -> listOf(XASP)
                else -> listOf()
            }
            Role.Spry -> when (caseType) {
                CaseType.UNCLOSE -> listOf(AJBW, BATX)
                CaseType.CLOSE -> listOf(AJBW)
                else -> listOf()
            }
            else -> listOf()
        }
    }
}

