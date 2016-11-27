package com.vslimit.kotlindemo.util.net.volley

import android.content.res.Resources
import com.android.volley.*
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.util.net.volley.FastJsonRequest
import com.vslimit.kotlindemo.util.net.volley.NormalPostRequest

fun VolleyError.toString(res: Resources): String {
    when {
        isApiError() -> return getApiErrorMessage(res)
        isNetworkError() -> return res.getString(R.string.no_data_connection)
        this is TimeoutError -> return res.getString(R.string.generic_server_down)
        else -> return res.getString(R.string.generic_error)
    }
}

private fun VolleyError.isNetworkError() = this is NetworkError || this is NoConnectionError

private fun VolleyError.isApiError() = this is ServerError || this is AuthFailureError

/** @return A user readable message that interprets the cause of the Api failure. */
private fun VolleyError.getApiErrorMessage(res: Resources): String {

    if (networkResponse != null) {
        when (networkResponse.statusCode) {
            404 -> return res.getString(R.string.no_results_found)
            422, 401, 411, 417 -> {
                return res.getString(R.string.generic_error)
            }
            else -> return res.getString(R.string.generic_server_down)
        }
    }
    return res.getString(R.string.generic_error)
}


inline fun <reified T : Any> RequestQueue.add(listener: Listener<T>?,
                                              url: String,
                                              noinline configure: ((Request<*>) -> Any)? = null,
                                              method: Int = Request.Method.GET) {

    val volleyListener = Response.Listener<T> { r -> listener?.onCompleted(null, r) }
    val errorListener = Response.ErrorListener { e -> listener?.onCompleted(e, null) }
    val request = FastJsonRequest(method, url, T::class.java, volleyListener, errorListener)
    configure?.invoke(request)
    this.add(request)
}

inline fun <reified T : Any> RequestQueue.post(listener: Listener<T>?,
                                               url: String,
                                               noinline configure: ((Request<*>) -> Any)? = null,
                                               param: MutableMap<String, String> = hashMapOf()) {

    val volleyListener = Response.Listener<T> { r -> listener?.onCompleted(null, r) }
    val errorListener = Response.ErrorListener { e -> listener?.onCompleted(e, null) }
    val request = NormalPostRequest(url, T::class.java, volleyListener, errorListener, param)
    configure?.invoke(request)
    this.add(request)
}
