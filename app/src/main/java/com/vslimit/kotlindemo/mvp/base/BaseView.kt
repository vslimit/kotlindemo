package com.vslimit.kotlindemo.mvp.base

/**
 * Created by sky on 17/1/6.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}