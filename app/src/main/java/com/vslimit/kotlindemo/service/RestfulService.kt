package com.vslimit.kotlindemo.service


import com.vslimit.kotlindemo.model.IPResult
import retrofit.http.*
import rx.Observable

/**
 * Created by vslimit on 16/7/22.
 */
interface RestfulService {

    //    @FormUrlEncoded
//    @POST("/login")
//    fun getLogin(@Field("q") q: String, @Field("dt") dt: String, @Field("key") key: String): Observable<Base<User>>
    @GET("/aaaa/getIpInfo.php")
    fun getIpInfo(@Query("ip") ip: String): Observable<IPResult>

    companion object {
        val SERVICE_ENDPOINT = "http://ip.taobao.com/service"
    }

}
