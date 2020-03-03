package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.user

enum class HomeMenu(val namez: String, url: String) {
    JGCX("机构查询", "mianJGCX"),
    XTGG("系统公告", "mianXTGG"),
    TSJY("投诉建议", "mianTSJY"),
    JDYH("鉴定摇号", "mianJDYH"),
    JGXX("机构信息", "mianJGXX"),
    AJXX("案件信息", "mianAJXX"),
    ZJXX("专家信息", "mianZJXX"),
    PSXX("评审信息", "mianPSXX"),
    WDAJ("我的案件", "mianWDAJ"),
    ;

    companion object {
        val all
            get():List<HomeMenu> {
                val list = ArrayList<HomeMenu>()
                list.addAll(listOf(JGCX, XTGG, TSJY, JDYH))
                if (!isLogin) return list

                // 登录情况下
                val roles = user.roles
                if (Role.JdjgAdmin.namez in roles)
                    list.add(JGXX)
                if (Role.Jdjg.namez in roles)
                    list.add(AJXX)
                if (Role.Pszj.namez in roles)
                    list.addAll(listOf(ZJXX, PSXX))
                if (Role.Normal.namez in roles)
                    list.add(WDAJ)
                return list
            }
    }

}