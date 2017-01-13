package com.vslimit.kotlindemo.mvp.ganks

import com.vslimit.kotlindemo.model.Gank
import com.vslimit.kotlindemo.mvp.base.BasePresenter
import com.vslimit.kotlindemo.mvp.base.BaseView

/**
 * Created by sky on 17/1/10.
 */
interface GanksContract {

    interface View : BaseView<Presenter> {
        fun showGanks(ganks: List<Gank>)
        fun showTip(message:String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter {
        fun loadGanks()
    }

}