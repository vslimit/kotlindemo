package com.vslimit.kotlindemo.mvp.users

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.activity.BaseActivity
import com.vslimit.kotlindemo.extensions.loadDaoSession
import com.vslimit.kotlindemo.mvp.data.UserDao
import com.vslimit.kotlindemo.mvp.ganks.GanksFragment
import com.vslimit.kotlindemo.mvp.ganks.GanksPresenter
import com.vslimit.kotlindemo.ui.DrawerLayoutManager
import org.jetbrains.anko.find

/**
 * Created by sky on 17/1/10.
 */
class UsersActivity(override val layoutResourceId: Int = R.layout.activity_tasks) : BaseActivity(), DrawerLayoutManager {

    override val drawerLayout by lazy { find<DrawerLayout>(R.id.drawer_layout) }
    val navigationView by lazy { find<NavigationView>(R.id.nav_view) }
    var mUsersPresenter: UsersPresenter? = null

    var userDao:UserDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDrawerContent(navigationView, R.id.drawLayoutTask)

        val usersFragment = UsersFragment.getInstance()
        userDao = loadDaoSession().userDao
        if (savedInstanceState == null) {
            initFragment(usersFragment)
        }

        mUsersPresenter = UsersPresenter(usersFragment, userDao as UserDao)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}