package com.unicorn.forensic2.app

import androidx.multidex.MultiDexApplication
import com.chibatching.kotpref.Kotpref

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Kotpref.init(this)
    }

}