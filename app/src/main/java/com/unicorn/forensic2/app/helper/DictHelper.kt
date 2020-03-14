package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.data.model.Dict
import io.reactivex.Single

abstract class DictHelper {

    abstract fun getFirstLevel(): Single<List<Dict>>

    abstract fun getChildren(id: String): Single<List<Dict>>

}