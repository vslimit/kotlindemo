package com.vslimit.kotlindemo.db

import java.util.*

/**
 * Created by vslimit on 17/1/26.
 */

data class Company(val map: MutableMap<String, Any?>) {
    var _id: Long by map
    var name: String by map
    var address: String by map

    constructor() : this(HashMap()) {
    }

    constructor(id:Long,name: String,address:String) : this(HashMap()) {
        this._id = id
        this.name = name
        this.address = address
    }

}

