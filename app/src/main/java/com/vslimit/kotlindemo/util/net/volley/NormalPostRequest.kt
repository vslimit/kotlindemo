package com.vslimit.kotlindemo.util.net.volley

import android.util.Log
import com.alibaba.fastjson.JSON
import com.android.volley.*
import com.android.volley.Response.Listener
import com.android.volley.toolbox.HttpHeaderParser
import org.json.JSONException
import java.io.UnsupportedEncodingException

/**
 * Created by vslimit on 16/5/26.
 */
class   NormalPostRequest<T>(url: String, private val clazz: Class<T>, private val mListener: Listener<T>, errorListener: Response.ErrorListener, private val mMap: Map<String, String>) : Request<T>(Method.POST, url, errorListener) {

    //mMap是已经按照前面的方式,设置了参数的实例
    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return mMap
    }

    //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        try {
            val jsonString = String(response.data)
            Log.d("return json:::", jsonString)
            return Response.success(JSON.parseObject(jsonString, clazz), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error<T>(ParseError(e))
        } catch (je: JSONException) {
            return Response.error<T>(ParseError(je))
        }
    }

    override fun deliverResponse(response: T) {
        mListener.onResponse(response)
    }

}
