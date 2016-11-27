package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.vslimit.kotlindemo.App
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.event.BaseEvent
import com.vslimit.kotlindemo.model.Result
import com.vslimit.kotlindemo.util.Bus
import com.vslimit.kotlindemo.util.NetworkUtil
import com.vslimit.kotlindemo.util.net.volley.Listener
import com.vslimit.kotlindemo.util.net.volley.add
import com.vslimit.kotlindemo.util.net.volley.toString
import com.vslimit.kotlindemo.fragment.BaseFragment
import com.vslimit.kotlindemo.model.IPResult
import kotlinx.android.synthetic.main.fragment_volley.*
import org.jetbrains.anko.async
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread


/**
 * Created by vslimit on 16/2/24.
 */
class VolleyFragment() : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_volley

    var result: IPResult? = null

    companion object {
        fun getInstance(): VolleyFragment {
            return VolleyFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        Bus.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    fun init() {
        if (NetworkUtil.isNetwork(act)) {
            val listener = Listener<IPResult> { e, r ->
                e?.let { Bus.post(BaseEvent<IPResult>(error = e)) }
                r?.let { Bus.post(BaseEvent(response = r)) }
            }
            val url = "http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42"
            Log.d("Url:::", "")
            App.queue!!.add(listener, url)
            toast("正在加载中...")
        } else {
            alert("网络错误", "网络未连接，请检查网络")
        }
    }

    fun onEventMainThread(event: BaseEvent<IPResult>) {
        val error = event.error
        result = event.response
        info(result)
        toast("加载完成!!!")
        if (error != null) {
            toast(error.toString(resources))
        } else {
            if (result!!.code == Result.SUCCESS) {
                async() {
                    uiThread {
                        resultTv.text = result!!.data!!.country
                    }
                }
            } else {
                toast(result!!.code)
            }
        }
    }


}