package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.app.defaultPageSize
import com.unicorn.forensic2.data.model.*
import com.unicorn.forensic2.data.model.param.AddTsjyParam
import com.unicorn.forensic2.data.model.param.AddTsjyReplyParam
import com.unicorn.forensic2.data.model.response.GeneralResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    // 鉴定机构公共列表
    @GET("public/jgList")
    fun getJdjgList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = 10): Single<Page<Jdjg>>

    // 鉴定机构公共详情
    @GET("public/jgDetail")
    fun getJdjgDetail(@Query("jgid") jdjgId: String): Single<Jdjg>

    // 获取鉴定机构我的
    @GET("api/v1/jdJdjg/basicInfo")
    fun getJdjgMy(): Single<Jdjg>


    // 系统公告
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

    @POST("api/v1/tsjy/{objectId}")
    fun addTsjyReply(@Path("objectId") tsjyId: String, @Body addTsjyReplyParam: AddTsjyReplyParam): Single<GeneralResponse>

    // 鉴定资质
    @Multipart
    @POST("api/v1/jdJdjgzzNew")
    fun createJgzz(@PartMap map: Map<String, @JvmSuppressWildcards RequestBody>, @Part vararg parts: MultipartBody.Part): Single<GeneralResponse>

    @DELETE("api/v1/jdJdjgzzNew/{objectId}")
    fun deleteJgzz(@Path("objectId") objectId: String): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdJdjgzzNew/{objectId}")
    fun editJgzz(
        @Path("objectId") jgzzId: String,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    // 鉴定人员
    @Multipart
    @POST("api/v1/jdJdryNew")
    fun createJdry(@PartMap map: Map<String, @JvmSuppressWildcards RequestBody>, @Part vararg parts: MultipartBody.Part): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdJdryNew/{objectId}")
    fun editJdry(
        @Path("objectId") jdryId: String,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @DELETE("api/v1/jdJdryNew/{objectId}")
    fun deleteJdry(@Path("objectId") objectId: String): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdJdjg/updateBasicInfo")
    fun editJdjgMy(
        @Query("jgid") jgId:String,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @POST("api/v1/jdJdjg/submitAudit")
    fun submitAudit(): Single<GeneralResponse>

    @GET("api/v1/jdJdjgSp/jg")
    fun getShjlJdjgList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = defaultPageSize): Single<Page<ShjlJdjg>>

    @GET("api/v1/jdReview/dpsList/vo")
    fun getPsxxList(@Query("page") page: Int, @Query("pageSize") pageSize: Int = defaultPageSize): Single<Page<Psxx>>

    // 字典
    // 鉴定类别
    @GET("category/jdlb")
    fun getJdlb(@Query("id") id: String): Single<List<Dict>>

    // 资质等级
    // 参数为鉴定类别Id
    @GET("category/zzdj")
    fun getZzdj(@Query("jdlb") jdlbId: String): Single<List<Dict>>

    // 机构所在地
    @GET("category/region")
    fun getCyly(@Query("id") id: String): Single<List<Dict>>

    // 机构性质
    @GET("category/jgxz")
    fun getJgxz(): Single<List<Jgxz>>

    // 资质类别
    @GET("category/zzlb")
    fun getZzlb(): Single<List<Zzlb>>


//    @POST("Authorization/UserLogin")
//    fun loginForSession(@Body userLogin: UserLogin): Call<Response<LoginResult>>


}