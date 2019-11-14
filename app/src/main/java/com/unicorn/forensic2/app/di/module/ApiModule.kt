package com.unicorn.forensic2.app.di.module

import com.unicorn.forensic2.app.V1
import com.unicorn.forensic2.data.api.V1Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    fun provideV1Api(@Named(V1) retrofit: Retrofit): V1Api = retrofit.create(V1Api::class.java)

}