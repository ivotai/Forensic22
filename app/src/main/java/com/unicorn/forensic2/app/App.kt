package com.unicorn.forensic2.app

import androidx.multidex.MultiDexApplication
import com.chibatching.kotpref.Kotpref
import com.mikepenz.iconics.Iconics
import com.unicorn.forensic2.refactor.icon.Fad
import com.unicorn.forensic2.refactor.icon.Fal
import com.unicorn.forensic2.refactor.icon.Far
import com.unicorn.forensic2.refactor.icon.Fas

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        Kotpref.init(this)
        initIconics()
    }

    private fun initIconics() {
        Iconics.init(applicationContext)
        Iconics.registerFont(Fal)
        Iconics.registerFont(Far)
        Iconics.registerFont(Fas)
        Iconics.registerFont(Fad)
    }

}