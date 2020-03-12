package com.unicorn.forensic2.ui.act

import com.unicorn.forensic2.app.Cyly
import com.unicorn.forensic2.app.helper.DictHelper
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.Single

class CylyTreeAct : TreeAct() {

    override val key =Cyly

    override val title = "机构所在地"

    override val dictHelper: DictHelper = object : DictHelper() {
        override fun getFirstLevel(): Single<List<Dict>> {
            return v1Api.getCyly(0)
        }

        override fun getChildren(id: Int): Single<List<Dict>> {
            return v1Api.getCyly(id)
        }
    }

}