package com.vslimit.kotlindemo.extensions

import com.vslimit.kotlindemo.App
import com.vslimit.kotlindemo.activity.BaseActivity

/**
 * Created by vslimit on 16/12/24.
 */

fun BaseActivity.loading(msg: Int) = handler.sendEmptyMessage(msg)

fun BaseActivity.loadDaoSession() = (application as App).getDaoSession()
