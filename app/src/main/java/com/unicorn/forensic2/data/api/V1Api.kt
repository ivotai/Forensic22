package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.data.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface V1Api {

    // 登录接口要这么写，不理解其中奥妙
    @FormUrlEncoded
    @POST("login/account")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginResult>

    //所在地
    // 一定要 post {} 才不会报错，不能传空。 @Body any: Any = Any()
    @POST("category/region")
    fun getDictRegion(@Body any: Any = Any()): Observable<Response<List<Dict>>>

    // 鉴定类别
    // 一定要 post {} 才不会报错
    @POST("category/jdlb")
    fun getDictJdlb(@Body any: Any = Any()): Observable<Response<List<Dict>>>

    // 机构性质
    // 机构性质不需要 post {}，莫名其妙
    @POST("category/jgxz")
    fun getDictJgxz(): Observable<Response<List<Dict>>>

    // 资质等级
    // 参数为鉴定类别Id
    @POST("category/DictZzdj")
    fun getDictZzdj(@Body keyParam: KeyParam): Observable<Response<List<Dict>>>


    @POST("Authorization/UserLogin")
    fun loginForSession(@Body userLogin: UserLogin): Call<Response<LoginResult>>

    // 案件
    @POST("Case/CaseMyList")
    fun getCaseList(@Body pageParam: PageParam<SearchParamCase>): Single<PageResponse<Case>>


}