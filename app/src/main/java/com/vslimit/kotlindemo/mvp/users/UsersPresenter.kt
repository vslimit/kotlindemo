package com.vslimit.kotlindemo.mvp.users

import com.vslimit.kotlindemo.mvp.data.User
import com.vslimit.kotlindemo.mvp.data.UserDao

/**
 * Created by sky on 17/1/11.
 */
class UsersPresenter : UsersContract.Presenter {

    var mUsersView: UsersContract.View? = null
    var mUserDao: UserDao? = null

    constructor(usersView: UsersContract.View, userDao: UserDao) {
        mUsersView = checkNotNull(usersView)
        mUsersView!!.setPresenter(this)
        mUserDao = userDao
    }

    override fun loadUsers() {
        val qb = mUserDao!!.queryBuilder()
        val list = qb.list()
        if (list.isEmpty() || list.size <= 0)  {
            for (i in 0..5) {
                val user:User = User()
                user.name = "aaa$i"
                user.password = i.toString()
                list.add(user)
            }
            mUserDao!!.insertInTx(list)
        }
        processUsers(list)
    }

    override fun start() {
        loadUsers()
    }

    fun processUsers(users: List<User>) {
        mUsersView!!.showUsers(users)
    }


}