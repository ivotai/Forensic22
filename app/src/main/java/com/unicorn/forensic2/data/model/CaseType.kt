package com.unicorn.forensic2.data.model

import com.unicorn.forensic2.app.user

enum class CaseType(val namez: String) {
    Jg("机构"),
    Zj("专家"),
    Dsr("当事人")
    ;

    companion object {
        val all
            get() = ArrayList<CaseType>().apply {
                add(Jg)
                add(Zj)
                add(Dsr)
                if (!user.JdjgAdmin)
                    remove(Jg)
                if (!user.Normal)
                    remove(Dsr)
                if (!user.Pszj)
                    remove(Zj)
            }
    }

}