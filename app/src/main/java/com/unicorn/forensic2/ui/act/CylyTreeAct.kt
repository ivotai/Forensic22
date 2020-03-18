package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.app.Cyly
import com.unicorn.forensic2.app.helper.TreeFetcher
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.Single

class CylyTreeAct : TreeAct() {

    override val key =Cyly

    override val title = "机构所在地"

    override val treeFetcher: TreeFetcher = object : TreeFetcher() {
        override fun getFirstLevel(): Single<List<Dict>> {
            return v1Api.getCyly(0.toString())
        }

        override fun getChildren(id: String): Single<List<Dict>> {
            return v1Api.getCyly(id)
        }
    }

}