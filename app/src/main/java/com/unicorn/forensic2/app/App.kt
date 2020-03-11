package com.unicorn.forensic2.app

import androidx.multidex.MultiDexApplication
import com.chibatching.kotpref.Kotpref
import com.facebook.stetho.Stetho
import com.unicorn.forensic2.app.helper.DictHelper

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Kotpref.init(this)
        Stetho.initializeWithDefaults(this)
        DictHelper.init()
    }

}