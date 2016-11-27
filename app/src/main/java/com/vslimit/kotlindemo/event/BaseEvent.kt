package com.vslimit.kotlindemo.event

import com.android.volley.VolleyError

/**
 * Created by Kittinun Vantasin on 10/18/14.
 */

open class BaseEvent<T>(val response: T? = null, val error: VolleyError? = null) {

}
