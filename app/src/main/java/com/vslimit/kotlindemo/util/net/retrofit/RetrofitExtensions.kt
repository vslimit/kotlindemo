package com.vslimit.kotlindemo.util.net.retrofit

import android.content.res.Resources
import com.vslimit.kotlindemo.R
import retrofit.RetrofitError

/**
 * Created by vslimit on 16/11/30.
 */
fun RetrofitError.toString(res: Resources): String {
    when (kind) {
        RetrofitError.Kind.HTTP -> return getApiErrorMessage(res)
        RetrofitError.Kind.NETWORK -> return res.getString(R.string.generic_server_down)
        RetrofitError.Kind.CONVERSION -> return res.getString(R.string.no_data_connection)
        RetrofitError.Kind.UNEXPECTED -> return res.getString(R.string.generic_error)
        else -> return res.getString(R.string.generic_error)
    }
}

private fun RetrofitError.getApiErrorMessage(res: Resources): String {
    if (response != null) {
        when (response.status) {
            404 -> return res.getString(R.string.no_results_found)
            422, 401, 411, 417 -> {
                return res.getString(R.string.generic_error)
            }
            else -> return res.getString(R.string.generic_server_down)
        }
    }
    return res.getString(R.string.generic_error)
}