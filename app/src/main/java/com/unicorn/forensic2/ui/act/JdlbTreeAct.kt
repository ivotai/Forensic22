package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.app.Jdlb
import com.unicorn.forensic2.app.helper.TreeFetcher
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.Single

class JdlbTreeAct : TreeAct() {

    override val key = Jdlb

    override val title = "鉴定类别"

    override val treeFetcher: TreeFetcher = object : TreeFetcher() {
        override fun getFirstLevel(): Single<List<Dict>> {
            return v1Api.getJdlb("")
        }

        override fun getChildren(id: String): Single<List<Dict>> {
            return v1Api.getJdlb(id)
        }
    }

}