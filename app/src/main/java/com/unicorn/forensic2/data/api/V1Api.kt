package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.data.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface V1Api {

    //所在地
    // 一定要 post {} 才不会报错，不能传空。 @Body any: Any = Any()
    @POST("Code/DictRegion")
    fun getDictRegion(@Body any: Any = Any()): Observable<Response<List<Dict>>>

    // 鉴定类别
    // 一定要 post {} 才不会报错
    @POST("Code/DictJdlb")
    fun getDictJdlb(@Body any: Any = Any()): Observable<Response<List<Dict>>>

    // 机构性质
    // 机构性质不需要 post {}，莫名其妙
    @POST("Code/DictJgxz")
    fun getDictJgxz(): Observable<Response<List<Dict>>>

    // 资质等级
    // 参数为鉴定类别Id
    @POST("Code/DictZzdj")
    fun getDictZzdj(@Body keyParam: KeyParam): Observable<Response<List<Dict>>>

    @POST("Authorization/UserLogin")
    fun login(@Body userLogin: UserLogin): Observable<Response<UserLoginResult>>

    @POST("Authorization/UserLogin")
    fun loginForSession(@Body userLogin: UserLogin): Call<Response<UserLoginResult>>

}