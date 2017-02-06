package com.vslimit.kotlindemo.realmobj

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by vslimit on 17/2/4.
 */
@RealmClass
open class Book : RealmObject(){
    @PrimaryKey
    open var id:String? = ""
    open var name:String? = ""
    open var author:String? = ""
}
