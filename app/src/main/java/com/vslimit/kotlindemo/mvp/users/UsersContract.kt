package com.vslimit.kotlindemo.mvp.users

import com.vslimit.kotlindemo.mvp.base.BasePresenter
import com.vslimit.kotlindemo.mvp.base.BaseView
import com.vslimit.kotlindemo.mvp.data.User

/**
 * Created by sky on 17/1/10.
 */
interface UsersContract {

    interface View : BaseView<Presenter> {
        fun showUsers(users: List<User>)
        fun showTip(message:String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter : BasePresenter {
        fun loadUsers()
    }

}