package com.unicorn.forensic2.app.di

import com.unicorn.forensic2.app.di.component.AppComponent
import com.unicorn.forensic2.app.di.component.DaggerAppComponent


object ComponentHolder {

    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }

}