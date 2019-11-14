package com.unicorn.forensic2.app.di.component

import com.unicorn.forensic2.app.di.module.ApiModule
import com.unicorn.forensic2.app.di.module.NetworkModule
import com.unicorn.forensic2.data.api.V1Api
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApiModule::class])
interface AppComponent {

    fun v1Api(): V1Api

}