package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.schedulers.Schedulers

object DictHelper {

    // 所在地
    lateinit var regions: List<Dict>

    // 鉴定类别
    lateinit var jdlb: List<Dict>

    fun getDicts() = with(v1Api) {
        getDictRegion()
            .flatMap {
                regions = it.data
                v1Api.getDictJdlb()
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                jdlb = it.data
            }
    }!!

    private val v1Api by lazy { ComponentHolder.appComponent.v1Api() }
}