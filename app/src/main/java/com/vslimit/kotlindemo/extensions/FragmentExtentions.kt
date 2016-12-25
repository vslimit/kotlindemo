package com.vslimit.kotlindemo.extensions

import com.vslimit.kotlindemo.activity.BaseActivity
import com.vslimit.kotlindemo.fragment.BaseFragment
import org.jetbrains.anko.support.v4.act

/**
 * Created by vslimit on 16/12/24.
 */

fun BaseFragment.loading(msg: Int) = (act as BaseActivity).loading(msg)
