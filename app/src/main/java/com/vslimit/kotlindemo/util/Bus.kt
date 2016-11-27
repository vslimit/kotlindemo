package com.vslimit.kotlindemo.util

import de.greenrobot.event.EventBus

/**
 * Created by vslimit on 16/1/20.
 */
open class Bus {
    companion object {
        val DEFAULT_BUS = EventBus.getDefault()

        fun register(target: Any): Unit = DEFAULT_BUS.register(target)
        fun unregister(target: Any): Unit = DEFAULT_BUS.unregister(target)
        fun post(event: Any): Unit = DEFAULT_BUS.post(event)
    }
}