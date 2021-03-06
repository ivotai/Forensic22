package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.app.defaultPageSize
import com.unicorn.forensic2.data.DsrCase
import com.unicorn.forensic2.data.model.*
import com.unicorn.forensic2.data.model.param.*
import com.unicorn.forensic2.data.model.response.GeneralResponse
import com.unicorn.forensic2.refactor.case1.detail.jdxx.Jdxx
import com.unicorn.forensic2.refactor.case1.detail.process.CaseProcess
import com.unicorn.forensic2.refactor.caseDemo.CaseDemo
import com.unicorn.forensic2.refactor.main.profile.backlog.Backlog
import com.unicorn.forensic2.refactor.stat.Stat
import com.unicorn.forensic2.refactor.tztx.Tztx
import com.unicorn.forensic2.ui.operation.hf.ReplyParam
import com.unicorn.forensic2.ui.operation.jdfk.JdrySimple
import com.unicorn.forensic2.ui.operation.lotteryDelay.LotteryDelay
import com.unicorn.forensic2.ui.operation.remind.Remind
import com.unicorn.forensic2.ui.operation.taskDoc.JdTaskDocParam
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface V1Api {

    // 登录接口要这么写，不理解其中奥妙
    @FormUrlEncoded
    @POST("login/{path}")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("court") court: String? = null,
        @Path(value = "path") path: String
    ): Single<LoginResult>

    @FormUrlEncoded
    @POST("login/{path}")
    fun loginSilently(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("court") court: String? = null,
        @Path(value = "path") path: String
    ): Call<LoginResult>


    // 注册
    @GET("public/verifyCode/register")
    fun getVerifyCodeForRegister(@Query("phoneNo") phoneNo: String): Single<GeneralResponse>

    @POST("register")
    fun register(@Body registerParam: RegisterParam): Single<GeneralResponse>

    @GET("public/verifyCode/forgotPassword")
    fun getVerifyCodeForForgotPassword(@Query("phoneNo") phoneNo: String): Single<GeneralResponse>

    @PATCH("public/forgotPassword")
    fun forgotPassword(@Body forgotPasswordParam: ForgotPasswordParam): Single<GeneralResponse>

    @PUT("api/v1/system/user/modifyPassword")
    fun modifyPassword(@Body modifyPasswordParam: ModifyPasswordParam): Single<GeneralResponse>

    // 注册机构
    @POST("api/v1/jdJdjg/register")
    fun registerJdjg(@Body registerJdjgParam: RegisterJdjgParam): Single<GeneralResponse>

    @GET("api/v1/current")
    fun getPersonalInfo(): Single<PersonalInfoResponse>

    @PATCH("api/v1/system/user/personalInfo")
    fun updatePersonalInfo(@Body updatePersonalInfoParam: UpdatePersonalInfoParam): Single<GeneralResponse>

    @GET("api/v1/home")
    fun getHomeInfo(): Single<HomeInfo>

    // 案件
    // 机构案件

    // 当事人
    @GET("api/v1/jdCase/myCase")
    fun getMyCaseList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<DsrCase>>

    //

    @GET("api/v1/jdLottery/zbtzList")
    fun getZbtzList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/jdLottery/djdList")
    fun getDjdList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/jdLottery/yjdList")
    fun getYjdList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/jdLottery/yjjList")
    fun getYjjList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/jdLottery/yxaList")
    fun getYxaList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    //

    @GET("api/v1/app/jdCase/unClose")
    fun getUnClose(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/app/jdCase/close")
    fun getClose(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    //

    @GET("api/v1/app/jdCase/acceptApproval")
    fun getAcceptApproval(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/app/jdCase/shakeAgainApproval")
    fun getShakeAgainApproval(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/app/jdCase/closeApproval")
    fun getCloseApproval(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/app/jdCase/destroyApproval")
    fun getDestroyApproval(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    //

    @GET("api/v1/jdLottery/yhhf")
    fun getJdxx(@Query("caseId") caseId: String, @Query("role") roleTag: String): Single<List<Jdxx>>

    //

    @GET("api/v1/jdCase/myCase")
    fun getCaseDsrList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Case>>

    @GET("api/v1/jdProcess")
    fun getCaseProcessList(
        @Query("caseId") caseId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<CaseProcess>>

    // 鉴定机构
    // 鉴定机构公共列表
    @GET("public/jgList")
    fun getJdjgList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>
    ): Single<Page<Jdjg>>

    // 鉴定机构公共详情
    @GET("public/jgDetail")
    fun getJdjgDetail(@Query("jgid") jdjgId: String): Single<Jdjg>

    // 获取鉴定机构我的
    @GET("api/v1/jdJdjg/basicInfo")
    fun getJdjgMy(): Single<Jdjg>


    // 系统公告
    // 系统公告列表
    @GET("public/xtggList")
    fun getXtggList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @Query("dataflag") vararg dataFlag: Int = intArrayOf(2, 3)
    ): Single<Page<Xtgg>>


    // 投诉建议
    // 已回复投诉建议列表
    @GET("api/v1/tsjy/reply")
    fun getTsjyYhfList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Tsjy>>

    // 未回复投诉建议列表
    @GET("api/v1/tsjy/noReply")
    fun getTsjyWhfList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Tsjy>>

    @POST("api/v1/tsjy")
    fun addTsjy(@Body addTsjyParam: AddTsjyParam): Single<GeneralResponse>

    @POST("api/v1/tsjy/{objectId}")
    fun addTsjyReply(
        @Path("objectId") tsjyId: String,
        @Body addTsjyReplyParam: AddTsjyReplyParam
    ): Single<GeneralResponse>

    // 鉴定资质
    @Multipart
    @POST("api/v1/jdJdjgzzNew")
    fun createJgzz(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

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
    fun createJdry(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part
    ): Single<GeneralResponse>

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
        @Query("jgid") jgId: String,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @POST("api/v1/jdJdjg/submitAudit")
    fun submitAudit(): Single<GeneralResponse>

    @GET("api/v1/jdJdjgSp/jg")
    fun getShjlJdjgList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any> = HashMap()
    ): Single<Page<Shjl>>

    //

    @GET("api/v1/jdReview/dpsList")
    fun getJdReviewDpsList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Psxx>>

    @GET("api/v1/jdReview/ywcList")
    fun getJdReviewYwcList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Psxx>>

    // 专家信息
    @GET("api/v1/jdExpertNew/basicInfo")
    fun getExpert(@Query("expertId") expertId: Long): Single<Expert>

    @GET("api/v1/jdJdjgSp/expert")
    fun getShjlExpertList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Shjl>>

    @Multipart
    @POST("api/v1/jdExpertNew/{objectId}")
    fun editExpert(
        @Path("objectId") jdryId: String,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdExpertNew")
    fun registerExpert(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    // 专家资质
    @GET("api/v1/jdExpertZzNew")
    fun getZjzzList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Zjzz>>

    @Multipart
    @POST("api/v1/jdExpertZzNew")
    fun addZjzz(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @DELETE("api/v1/jdExpertZzNew/{objectId}")
    fun deleteZjzz(@Path("objectId") objectId: String): Single<GeneralResponse>

    // 摇号列表
    @GET("public/rollList")
    fun getRollList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>
    ): Single<Page<Roll>>


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

    // 法院
    @GET("category/fy")
    fun getFy(@Query("fydm") fydm: String): Single<List<Fy>>

    // 机构性质
    @GET("category/jgxz")
    fun getJgxz(): Single<List<Jgxz>>

    // 资质类别
    @GET("category/zzlb")
    fun getZzlb(): Single<List<Zzlb>>


//    @POST("Authorization/UserLogin")
//    fun loginForSession(@Body userLogin: UserLogin): Call<Response<LoginResult>>


    //

    @POST("api/v1/app/jdLottery/reply")
    fun reply(@Body replyParam: ReplyParam): Single<GeneralResponse>

    @POST("api/v1/jdTaskDoc")
    fun jdTaskDoc(@Body jdTaskDocParam: JdTaskDocParam): Single<GeneralResponse>

    @GET("api/v1/jdLottery/lotteryDelay")
    fun lotteryDelay(
        @Query("lid") lid: String?,
        @Query("caseId") caseId: String
    ): Single<List<LotteryDelay>>


    @GET("api/v1/jdJdry/list")
    fun jdJdryList(
        @Query("jgid") jgid: String?,
        @Query("jdlb") jdlbId: String
    ): Single<List<JdrySimple>>

//    @POST(" api/v1/jdLottery/addLotteryDelay")
//    fun addLotteryDelay(@Body addLotteryDelayParam: AddLotteryDelayParam): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdLottery/addLotteryDelay")
    fun addLotteryDelay(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>
    ): Single<GeneralResponse>

    @GET("api/v1/app/backlog")
    fun backlog(@Query("role") roleTag: String): Single<List<Backlog>>

    @GET("api/v1/notification/new")
    fun notificationNew(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Tztx>>

    @GET("api/v1/notification/old")
    fun notificationOld(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize
    ): Single<Page<Tztx>>

    @GET("api/v1/jdRemind")
    fun jdRemind(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @Query("caseId") caseId: String
    ): Single<Page<Remind>>

    @Multipart
    @POST("api/v1/jdRemind")
    fun jdRemind(@PartMap map: Map<String, @JvmSuppressWildcards RequestBody>): Single<GeneralResponse>


    @GET(" api/v1/jdCaseMemo")
    fun jdCaseMemo(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = defaultPageSize,
        @Query("caseId") caseId: String,
        @Query("pid") pid: String? = null
    ): Single<Page<CaseDemo>>

    @Multipart
    @POST("api/v1/jdCaseMemo")
    fun jdCaseMemo(@PartMap map: Map<String, @JvmSuppressWildcards RequestBody>): Single<GeneralResponse>

    @GET("api/v1/app/jdCase/sjcStatistics")
    fun stat(
        @Query("beginDate") beginDate: String,
        @Query("endDate") endDate: String,
        @Query("fydm") fydm: String
    ): Single<List<Stat>>


    //

    @GET("category/fy")
    fun getFy(): Single<List<Fy>>

    @Multipart
    @POST("api/v1/jdLottery/submitResult")
    fun jdLotterySubmitResult(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @Multipart
    @POST("api/v1/jdLottery/addLotteryDelay")
    fun jdLotteryAddLotteryDelay(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part vararg parts: MultipartBody.Part?
    ): Single<GeneralResponse>

    @GET(value = "public/checkUpdate")
    fun checkUpdate(
        @Query("versionName") versionName: String
    ): Observable<CheckUpdateResponse>

}