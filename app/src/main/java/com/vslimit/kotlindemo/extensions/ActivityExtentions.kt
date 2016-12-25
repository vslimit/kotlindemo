package com.vslimit.kotlindemo.extensions

import com.vslimit.kotlindemo.activity.BaseActivity

/**
 * Created by vslimit on 16/12/24.
 */

fun BaseActivity.loading(msg: Int) = handler.sendEmptyMessage(msg)
