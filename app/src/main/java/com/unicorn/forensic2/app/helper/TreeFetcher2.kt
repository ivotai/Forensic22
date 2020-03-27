package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.data.model.Fy
import io.reactivex.Single

abstract class TreeFetcher2 {

    abstract fun getFirstLevel(): Single<List<Fy>>

    abstract fun getChildren(fydm: String): Single<List<Fy>>

}