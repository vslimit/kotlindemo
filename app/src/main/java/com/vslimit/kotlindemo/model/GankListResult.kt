package com.vslimit.kotlindemo.model

import java.util.*

/**
 * Created by vslimit on 16/11/26.
 */
open class GankListResult {
    var count: Int = 0
    var error: Boolean = false
    var results: ArrayList<Gank> = ArrayList()
}

class Gank {
    var desc = ""
    var ganhuo_id = ""
    var publishedAt = ""
    var readability = ""
    var type = ""
    var url = ""
    var who = ""
}
