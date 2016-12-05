package com.vslimit.kotlindemo.model.net.volley

import android.util.Log
import com.alibaba.fastjson.JSON
import com.android.volley.*
import com.android.volley.Response.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.Response.Listener

/**
 * Created by vslimit on 16/1/19.
 */
open class FastJsonRequest<T> : Request<T> {

    private val clazz: Class<T>
    private val headerz: MutableMap<String, String> by lazy { hashMapOf("Accept" to "application/json") }
    private val listener: Listener<T>

    private var param: MutableMap<String, String> = hashMapOf()

    constructor(url: String, clazz: Class<T>, listener: Listener<T>, errorListener: ErrorListener) :
    super(Method.GET, url, errorListener) {
        this.clazz = clazz
        this.listener = listener
    }

    constructor(method: Int, url: String, clazz: Class<T>, listener: Listener<T>, errorListener: ErrorListener) :
    super(method, url, errorListener) {
        this.clazz = clazz
        this.listener = listener
    }

    constructor(method: Int, url: String, clazz: Class<T>, listener: Listener<T>, errorListener: ErrorListener, param: MutableMap<String, String>) :
    super(method, url, errorListener) {
        this.clazz = clazz
        this.listener = listener
        this.param = param
    }

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> = headerz

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> = param


    override fun deliverResponse(response: T) = listener.onResponse(response)

    override fun parseNetworkResponse(response: NetworkResponse?): Response<T>? {
        try {
            val json = String(response!!.data)
            Log.d("return json:::", json)
            return success(JSON.parseObject(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
        } catch(e: Exception) {
            return error<T>(ParseError(e))
        }

    }


}