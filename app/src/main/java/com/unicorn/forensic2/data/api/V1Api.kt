package com.unicorn.forensic2.data.api

import com.unicorn.forensic2.data.model.Dict
import com.unicorn.forensic2.data.model.Response
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface V1Api {

    //所在地
    @POST("Code/DictRegion")
    fun getDictRegion(@Body any: Any = Any()): Observable<Response<List<Dict>>>

    // 鉴定类别
    @POST("Code/DictJdlb")
    fun getDictJdlb(@Body any: Any = Any()): Observable<Response<List<Dict>>>

//    @POST("Code/DictZzdj")
//    fun getDictZzdj(@Body keyParam: KeyParam): Observable<Response<List<Dict>>>

//    @POST("Code/DictJgxz")
//    fun getDictJgxz(): Observable<Response<List<Dict>>>
}