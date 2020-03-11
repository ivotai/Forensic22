package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.app.defaultPageSize
import com.unicorn.forensic2.data.model.*
import com.unicorn.forensic2.data.model.param.AddTsjyParam
import com.unicorn.forensic2.data.model.param.AddTsjyReplyParam
import com.unicorn.forensic2.data.model.response.GeneralResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface V1Api {

    // 登录接口要这么写，不理解其中奥妙
    @FormUrlEncoded
    @POST("login/account")
    fun login(@Field("username") username: String, @Field("password") password: String): Single<LoginResult>

    // 案件
    // 机构案件
    @GET("api/v1/jdLottery/djdList")
    fun getDjdList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = defaultPageSize): Single<Page<Case>>

    // 鉴定机构
    // 鉴定机构列表
    @GET("public/jgList")
    fun getJdjgList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = 10): Single<Page<Jdjg>>

    // 鉴定机构详情
    @GET("public/jgDetail")
    fun getJdjgDetail(@Query("jgid") jdjgId: String): Single<Jdjg>

    // 获取已注册的鉴定机构
    @GET("api/v1/jdJdjg/basicInfo")
    fun getMyJdjg(): Single<Jdjg>

    // 获取机构资质列表 jgzzList


    // 系统公告列表
    @GET("public/xtggList")
    fun getXtggList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = defaultPageSize): Single<Page<Xtgg>>


    // 投诉建议
    // 已回复投诉建议列表
    @GET("api/v1/tsjy/reply")
    fun getTsjyYhfList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = 10): Single<Page<Tsjy>>

    // 未回复投诉建议列表
    @GET("api/v1/tsjy/noReply")
    fun getTsjyWhfList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = 10): Single<Page<Tsjy>>

    @POST("api/v1/tsjy")
    fun addTsjy(@Body addTsjyParam: AddTsjyParam): Single<GeneralResponse>

    @POST("api/v1/tsjy/{tsjyId}")
    fun addTsjyReply(@Path("tsjyId") tsjyId: String, @Body addTsjyReplyParam: AddTsjyReplyParam): Single<GeneralResponse>

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


}