package com.unicorn.forensic2.app.di.module

import com.google.gson.Gson
import com.unicorn.forensic2.app.V1
import com.unicorn.forensic2.app.baseUrl
import com.unicorn.forensic2.app.helper.NetworkHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson().newBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    @Named(V1)
    @Singleton
    @Provides
    fun provideOkHttpClient(gson: Gson): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val encodedPathSegments = chain.request().url.encodedPathSegments
                val flag = "category" in encodedPathSegments || "login" in encodedPathSegments
                if (flag) chain.proceed(chain.request())
                else NetworkHelper.proceedRequestWithSession(chain)
            }
//            .addInterceptor { chain ->
//                // 非空检测，虽然不是很必要
//                val response = chain.proceed(chain.request())
//                if (response.body == null) return@addInterceptor response
//                val body = response.body!!
//                // 判断是否登录超时
//                val content = body.string()
//                val errorCode = gson.fromJson(content, ErrorCode::class.java)
//                // 如果没有登录超时，返回新的 response
//                if (errorCode.errorCode != "0101") {
//                    return@addInterceptor response.newBuilder()
//                        .body(content.toResponseBody(body.contentType()))
//                        .build()
//                }
//                // 如果登录超时，自动登录
//                AppHelper.autoLogin()
//                // 重复登录超时的请求
//                AppHelper.proceedSidRequest(chain)
//            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Named(V1)
    @Singleton
    @Provides
    fun provideRetrofit(@Named(V1) okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

//    @Named(V2)
//    @Singleton
//    @Provides
//    fun provideOkHttpClient2(gson: Gson): OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor { chain -> AppHelper.proceedSidRequest2(chain) }
//            .addInterceptor { chain ->
//                // 非空检测，虽然不是很必要
//                val response = chain.proceed(chain.request())
//                if (response.body == null) return@addInterceptor response
//                val body = response.body!!
//                // 判断是否登录超时
//                val content = body.string()
//                val errorCode = gson.fromJson(content, ErrorCode::class.java)
//                // 如果没有登录超时，返回新的 response
//                if (errorCode.errorCode != "0101") {
//                    return@addInterceptor response.newBuilder()
//                        .body(content.toResponseBody(body.contentType()))
//                        .build()
//                }
//                // 如果登录超时，自动登录
//                AppHelper.autoLogin2()
//                // 重复登录超时的请求
//                AppHelper.proceedSidRequest2(chain)
//            }
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//
//    @Named(V2)
//    @Singleton
//    @Provides
//    fun provideRetrofit2(@Named(V2) okHttpClient: OkHttpClient, gson: Gson): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(baseUrl2)
//            .client(okHttpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()

}