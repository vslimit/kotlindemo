package com.vslimit.kotlindemo.model

/**
 * Created by vslimit on 16/11/27.
 */

class IPResult:Result<IP>()

class IP {
    var country_id:String? = null
    var country:String? = null
    var area_id:String? = null
    var region:String? = null
    var region_id:String? = null
    var city:String? = null
    var city_id:String? = null
    var ip:String? = null
}