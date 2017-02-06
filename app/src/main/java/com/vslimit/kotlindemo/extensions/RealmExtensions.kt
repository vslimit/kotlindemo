package com.vslimit.kotlindemo.extensions

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.RealmResults

/**
 * Created by Kittinun Vantasin on 10/20/14.
 */

fun <T : RealmObject> Realm.create(clazz: Class<T>, f: (it: T) -> Unit): T {
    beginTransaction()
    val realmObject = createObject(clazz)
    f(realmObject)
    commitTransaction()
    return realmObject
}

fun <T : RealmObject> Realm.create(it: T): T {
    beginTransaction()
    val realmObject = copyToRealm(it)
    commitTransaction()
    return realmObject
}

fun <T : RealmObject> Realm.createOrUpdate(it: T): T {
    beginTransaction()
    val realmObject = copyToRealmOrUpdate(it)
    commitTransaction()
    return realmObject
}

fun <T : RealmObject> Realm.deleteAll(clazz: Class<T>) {
    beginTransaction()
    val results = where(clazz).findAll()
    results.deleteAllFromRealm()
    commitTransaction()
}

fun <T : RealmObject> Realm.delete(clazz: Class<T>, key: String, value: String) {
    beginTransaction()
    val results = where(clazz).equalTo(key, value).findAll()
    results.deleteAllFromRealm()
    commitTransaction()
}

fun <T : RealmObject> Realm.query(clazz: Class<T>, conditionMap: Map<String, String>?): RealmResults<T> {
    val query: RealmQuery<T> = where(clazz)
    if (conditionMap != null) {
        for ((key, value) in conditionMap) {
            query.equalTo(key, value)
        }
    }
    return query.findAll()
}

fun <T : RealmObject> Realm.show(clazz: Class<T>,conditionMap: Map<String, String>?): T {
    val query: RealmQuery<T> = where(clazz)
    if (conditionMap != null) {
        for ((key, value) in conditionMap) {
            query.equalTo(key, value)
        }
    }
    return query.findFirst()
}

fun <T : RealmObject> Realm.findAll(clazz: Class<T>): RealmResults<T> {
    return query(clazz, null)
}

