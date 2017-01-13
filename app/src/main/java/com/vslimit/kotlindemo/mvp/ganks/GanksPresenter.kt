package com.vslimit.kotlindemo.mvp.ganks

import com.vslimit.kotlindemo.model.Gank
import com.vslimit.kotlindemo.model.GankListResult
import com.vslimit.kotlindemo.service.RestfulService
import com.vslimit.kotlindemo.service.ServiceFactory
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by sky on 17/1/11.
 */
class GanksPresenter : GanksContract.Presenter {

    var mGanksView: GanksContract.View? = null

    constructor(ganksView: GanksContract.View) {
        mGanksView = checkNotNull(ganksView)
        mGanksView!!.setPresenter(this)
    }

    override fun loadGanks() {
        mGanksView!!.showLoading()
        val service = ServiceFactory.createRetrofitService(RestfulService::class.java, RestfulService.GANK_SERVICE_ENDPOINT)
        service.loadGanks().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<GankListResult>() {
            override fun onCompleted() {
                mGanksView!!.hideLoading()
            }

            override fun onError(e: Throwable) {
                mGanksView!!.showTip(e.toString())
            }

            override fun onNext(result: GankListResult) {
                processGanks(result.results)
            }
        })
    }

    override fun start() {
        loadGanks()
    }

    fun processGanks(ganks: List<Gank>) {
        mGanksView!!.showGanks(ganks)
    }


}