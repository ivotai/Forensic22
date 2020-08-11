package com.unicorn.forensic2.refactor.home

import com.mikepenz.iconics.typeface.IIcon
import com.unicorn.forensic2.app.role
import com.unicorn.forensic2.data.model.Role
import com.unicorn.forensic2.refactor.icon.Light
import com.unicorn.forensic2.refactor.role.RoleWrapper

enum class HomeOperation(val namez: String, val icon: IIcon) {

    JGCX("机构查询", Light.Icon.light_accessible_icon),
    XTGG("系统公告", Light.Icon.light_accessible_icon),
    TSJY("投诉建议", Light.Icon.light_accessible_icon),
    WDAJ("我的案件", Light.Icon.light_accessible_icon),
    SPSX("审批事项", Light.Icon.light_accessible_icon),
    WDPS("我的评审", Light.Icon.light_accessible_icon),
    TZTX("通知提醒", Light.Icon.light_accessible_icon),
    JGXX("机构信息", Light.Icon.light_accessible_icon),
    ZJXX("专家信息", Light.Icon.light_accessible_icon),
    PSXX("评审信息", Light.Icon.light_accessible_icon),
    ZTBG("专题报告", Light.Icon.light_accessible_icon),
    ;

    companion object {
        val all
            get():List<HomeOperation> = when (role) {
                Role.Normal -> listOf(JGCX, XTGG, TSJY, WDAJ)
                Role.JdjgAdmin -> listOf(JGCX, XTGG, TSJY, WDAJ, TZTX, JGXX)
                Role.Pszj -> listOf(JGXX, XTGG, TSJY, WDPS, TZTX, ZJXX)
                Role.Spry -> listOf(JGCX, XTGG, WDAJ, TZTX, ZTBG)
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
                    list
                }
                else -> listOf(JGCX, XTGG)
            }
    }

}