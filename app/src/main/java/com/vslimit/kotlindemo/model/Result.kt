package com.vslimit.kotlindemo.model

/**
 * Created by vslimit on 16/11/26.
 */
open class Result<T> {
    open var code: Int = SUCCESS
    open var data: T? = null

    companion object {
        val SUCCESS = 0
    }
}