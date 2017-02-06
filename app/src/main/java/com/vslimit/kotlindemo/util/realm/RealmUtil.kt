package com.vslimit.kotlindemo.util.realm

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by vslimit on 16/11/8.
 */
class RealmUtil() {
    companion object {
        fun instant(): Realm {
            val config = RealmConfiguration.Builder().name("myrealm.realm").schemaVersion(1).migration(Migration.getInstance()).build()
            return Realm.getInstance(config)
        }
    }
}