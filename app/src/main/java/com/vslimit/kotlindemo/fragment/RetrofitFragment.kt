package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.model.IPResult
import com.vslimit.kotlindemo.service.RestfulService
import com.vslimit.kotlindemo.service.ServiceFactory
import com.vslimit.kotlindemo.util.net.retrofit.toString
import kotlinx.android.synthetic.main.fragment_volley.*
import org.jetbrains.anko.support.v4.toast
import retrofit.RetrofitError
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by vslimit on 16/11/29.
 */
class RetrofitFragment : BaseFragment() {

    override val layoutResourceId: Int = R.layout.fragment_volley

    companion object {
        fun getInstance(): RetrofitFragment {
            return RetrofitFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        toast("正在加载中...")
        val service = ServiceFactory.createRetrofitService(RestfulService::class.java, RestfulService.SERVICE_ENDPOINT)
        service.getIpInfo("63.223.108.42").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<IPResult>() {
            override fun onCompleted() {
                toast("加载完成!!!")
            }

            override fun onError(e: Throwable) {
                toast(if (e is RetrofitError) e.toString(resources) else e.toString())
            }

            override fun onNext(result: IPResult) {
                resultTv.text = result.data!!.country
            }
        })
    }
}