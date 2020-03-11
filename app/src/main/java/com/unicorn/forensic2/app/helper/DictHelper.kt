package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

object DictHelper {

    fun init() = with(v1Api) {
        getJdlb()
            .flatMap {
                jdlb = it
                getRegion(0)
            }
            .subscribeOn(Schedulers.io())
            .subscribeBy {
//                regions = it
            }
//        getDictRegion()
//            .flatMap {
//                regions = it.data
//                v1Api.getJdlb()
//            }
//            .flatMap {
//                jdlb = it.data
//                v1Api.getDictJgxz()
//            }
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                jgxz = it.data
//            }
    }!!

    // 所在地
    lateinit var regions: List<Dict>

    // 鉴定类别
    lateinit var jdlb: List<Dict>

    // 机构性质
    lateinit var jgxz: List<Dict>

    private val v1Api by lazy { ComponentHolder.appComponent.v1Api() }

}