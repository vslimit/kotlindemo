package com.vslimit.kotlindemo

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.Volley
import com.facebook.stetho.Stetho
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.OkUrlFactory
import com.vslimit.kotlindemo.mvp.data.DaoMaster
import com.vslimit.kotlindemo.mvp.data.DaoSession
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
        var queue: RequestQueue? = null
    }

    private var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        queue = initQueue()
        Stetho.initializeWithDefaults(this);
        initDao()
    }

    fun initDao() {
        val helper = DaoMaster.DevOpenHelper(this, "user")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun initQueue(): RequestQueue {
        val factory = OkUrlFactory(OkHttpClient())
        val hurlStack = object : HurlStack() {
            @Throws(IOException::class) override fun createConnection(url: URL): HttpURLConnection {
                return factory.open(url)
            }
        }
        return Volley.newRequestQueue(this, hurlStack)
    }

    open fun getDaoSession(): DaoSession {
        return daoSession!!
    }
}