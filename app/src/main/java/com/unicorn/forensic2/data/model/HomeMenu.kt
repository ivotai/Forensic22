package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.role

enum class HomeMenu(val namez: String, val imgRes: Int) {
    JGCX("机构查询", R.mipmap.jgcx),
    XTGG("系统公告", R.mipmap.xtgg),
    TSJY("投诉建议", R.mipmap.tsjy),
    WDAJ("我的案件", R.mipmap.wdaj),
    WDPS("我的评审", R.mipmap.wdaj),   // todo 替换图标
    TZTX("通知提醒", R.mipmap.jdyh),   // todo 替换图标
    JGXX("机构信息", R.mipmap.jgxx),

    //    AJXX("案件信息", R.mipmap.ajxx),
    ZJXX("专家信息", R.mipmap.zjxx),
    PSXX("评审信息", R.mipmap.psxx),
    ZTBG("专题报告", R.mipmap.psxx),    // todo 专题报告
    ;

    companion object {
        val all
            get():List<HomeMenu> = when (role) {
                Role.Normal -> listOf(JGCX, XTGG, TSJY, WDAJ)
                Role.JdjgAdmin -> listOf(JGCX, XTGG, TSJY, WDAJ, TZTX, JGXX)
                Role.Pszj -> listOf(JGXX, XTGG, TSJY, WDPS, TZTX, ZJXX)
                Role.Spry,
                Role.Sfjd,
                Role.SfjdAdmin -> listOf(JGCX, XTGG, WDAJ, TZTX, ZTBG)
                else -> listOf(JGCX, XTGG)
            }
    }

}