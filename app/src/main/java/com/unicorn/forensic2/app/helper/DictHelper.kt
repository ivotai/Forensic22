package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.data.model.Dict
import io.reactivex.schedulers.Schedulers

object DictHelper {

    lateinit var regions: List<Dict>

    fun getDicts() {
       v1Api.getDictRegion()
            .subscribeOn(Schedulers.io())
            .subscribe {
                regions = it.data
            }
    }

    private val v1Api by lazy { ComponentHolder.appComponent.v1Api() }
}