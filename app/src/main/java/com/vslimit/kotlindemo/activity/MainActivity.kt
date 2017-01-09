package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.adapter.TabAdapter
import com.vslimit.kotlindemo.model.TabTagEnum
import com.vslimit.kotlindemo.ui.DrawerLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class MainActivity : BaseActivity(),DrawerLayoutManager {

    override val layoutResourceId: Int = R.layout.activity_main

    internal var items = TabTagEnum.values().asList()

    override val drawerLayout by lazy { find<DrawerLayout>(R.id.drawer_layout) }
    val navigationView by lazy { find<NavigationView>(R.id.nav_view) }

    var adapter: TabAdapter? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            initFragment(TabTagEnum.HOME.fragment)
        }

        setupDrawerContent(navigationView, R.id.drawLayoutMain)
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 4)
        tabRv.layoutManager = layoutManager
        adapter = TabAdapter(items) {
            switchContent(items[adapter!!.pos].fragment, it.fragment)
            adapter!!.pos = it.ordinal
            adapter!!.notifyDataSetChanged()
        }

        tabRv.adapter = adapter
        adapter!!.notifyDataSetChanged()
        info("onCreate")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Open the navigation drawer when the home icon is selected from the toolbar.
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        info("onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        info("onStart")
    }

    override fun onResume() {
        super.onResume()
        info("onResume")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        info("onSaveInstanceState")
    }


    override fun onPause() {
        super.onPause()
        info("onPause")
    }

    override fun onStop() {
        super.onStop()
        info("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        info("onDestroy")
    }


}
