package com.vslimit.kotlindemo.service

import retrofit.RestAdapter

/**
 * Created by vslimit on 16/7/22.
 */
object ServiceFactory {
    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T {
        return RestAdapter.Builder().setEndpoint(endPoint).build().create(clazz)
    }
}
