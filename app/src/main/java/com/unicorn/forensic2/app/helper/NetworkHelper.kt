package com.unicorn.forensic2.app.helper

import com.unicorn.forensic2.app.Cookie
import com.unicorn.forensic2.app.SESSION
import com.unicorn.forensic2.app.di.ComponentHolder
import com.unicorn.forensic2.app.isLogin
import com.unicorn.forensic2.app.session
import okhttp3.Interceptor
import okhttp3.Response

object NetworkHelper {

//    fun proceedRequestWithNewSession(chain: Interceptor.Chain): Response {
//        // session 过期时使用 token 登录，获取新的 session 和 token。
//        api.loginSilently().execute().body().let { Globals.loginResponse = it!! }
//        return proceedRequestWithSession(chain)
//    }

    fun proceedRequestWithSession(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (isLogin)
            builder.removeHeader(Cookie).addHeader(Cookie, "${SESSION}=${session}")
        return builder.build().let { chain.proceed(it) }
    }

//    fun proceedRequestWithSession(chain: Interceptor.Chain): Response {
//        return chain.request().newBuilder()
//            .removeHeader(Cookie)
//            .addHeader(Cookie, "${SESSION}=${Globals.session}")
//            .build()
//            .let { chain.proceed(it) }
//    }

    private val api by lazy { ComponentHolder.appComponent.v1Api() }

}