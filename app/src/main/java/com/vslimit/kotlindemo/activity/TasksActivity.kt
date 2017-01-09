package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.ui.DrawerLayoutManager
import org.jetbrains.anko.find

class TasksActivity(override val layoutResourceId: Int = R.layout.activity_tasks ) : BaseActivity(),DrawerLayoutManager {
    override val drawerLayout by lazy { find<DrawerLayout>(R.id.drawer_layout) }
    val navigationView by lazy { find<NavigationView>(R.id.nav_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDrawerContent(navigationView, R.id.drawLayoutTask)
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
