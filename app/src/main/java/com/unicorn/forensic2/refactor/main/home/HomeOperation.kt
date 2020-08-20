package com.unicorn.forensic2.refactor.main.home

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.role.RoleWrapper

enum class HomeOperation(val namez: String, val iconRes: Int) {

    JGCX("机构查询", R.mipmap.jgcx),
    XTGG("系统公告", R.mipmap.xtgg),
    TSJY("投诉建议", R.mipmap.tsjy),
    WDAJ("我的案件", R.mipmap.wdaj),
    SPSX("审批事项", R.mipmap.spsx),
    WDPS("我的评审", R.mipmap.wdps),
    TZTX("通知提醒", R.mipmap.tztx),
    JGXX("机构信息", R.mipmap.jgxx),
    ZJXX("专家信息", R.mipmap.zjxx),
//    PSXX("评审信息", R.mipmap.psxx),
    ZTBG("专题报告", R.mipmap.ztbg),
    SJCTX("收结存统计",R.mipmap.sjctx),
    ;

    companion object {
        val all
            get():List<HomeOperation> = when (role) {
                Role.Normal -> listOf(JGCX, XTGG, TSJY, WDAJ)
                Role.JdjgAdmin -> listOf(JGCX, XTGG, TSJY, WDAJ, TZTX, JGXX)
                Role.Pszj -> listOf(JGXX, XTGG, TSJY, WDPS, TZTX, ZJXX)
                Role.Spry -> listOf(JGCX, XTGG, WDAJ, TZTX, ZTBG,SJCTX)
                Role.Sfjd, Role.SfjdAdmin -> {
                    val list = ArrayList<HomeOperation>()
                    list.add(JGCX)
                    list.add(XTGG)
                    if (Role.Sfjd in RoleWrapper.SfjdMixture.roles)
                        list.add(WDAJ)
                    if (Role.SfjdAdmin in RoleWrapper.SfjdMixture.roles)
                        list.add(SPSX)
                    list.add(TZTX)
                    list.add(ZTBG)
                    list.add(SJCTX)
                    list
                }
                else -> listOf(JGCX, XTGG)
            }
    }

}