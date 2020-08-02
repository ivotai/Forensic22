package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.R
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.user

enum class HomeMenu(val namez: String, url: String, val imgRes: Int) {
    JGCX("机构查询", "mianJGCX", R.mipmap.jgcx),
    XTGG("系统公告", "mianXTGG", R.mipmap.xtgg),
    TSJY("投诉建议", "mianTSJY", R.mipmap.tsjy),
//    JDYH("鉴定摇号", "mianJDYH", R.mipmap.jdyh),
    JGXX("机构信息", "mianJGXX", R.mipmap.jgxx),
    AJXX("案件信息", "mianAJXX", R.mipmap.ajxx),
    ZJXX("专家信息", "mianZJXX", R.mipmap.zjxx),
    PSXX("评审信息", "mianPSXX", R.mipmap.psxx),
    WDAJ("我的案件", "mianWDAJ", R.mipmap.wdaj),
    ;

    companion object {
        val all
            get():List<HomeMenu> {
                val list = ArrayList<HomeMenu>()
                list.addAll(listOf(JGCX, XTGG, TSJY))
                if (!isLogin) return list

                // 登录情况下
                val roles = user.roles
                if (Role.JdjgAdmin.cn in roles)
                    list.add(JGXX)
//                if (Role.Jdjg.namez in roles)
//                    list.add(AJXX)
                if (Role.Pszj.cn in roles)
                    list.addAll(listOf(ZJXX, PSXX))
                if (Role.Normal.cn in roles)
                    list.add(WDAJ)
                return list
            }
    }

}