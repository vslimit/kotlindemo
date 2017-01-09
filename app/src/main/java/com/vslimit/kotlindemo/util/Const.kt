package com.vslimit.kotlindemo.util

import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.activity.MainActivity
import com.vslimit.kotlindemo.activity.TasksActivity

/**
 * Created by vslimit on 16/12/24.
 */
class Const {
    companion object {
        val SHOW = 1
        val HIDE = 0

        val DRAWER_MAP = hashMapOf(R.id.drawLayoutMain to MainActivity::class.java, R.id.drawLayoutTask to TasksActivity::class.java)
    }

}