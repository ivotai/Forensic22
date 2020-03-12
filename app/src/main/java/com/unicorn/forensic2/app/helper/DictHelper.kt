package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.data.model.Dict
import io.reactivex.Single

abstract class DictHelper {

    abstract fun getLevel1(): Single<List<Dict>>

    abstract fun getChildren(id: Int): Single<List<Dict>>

}