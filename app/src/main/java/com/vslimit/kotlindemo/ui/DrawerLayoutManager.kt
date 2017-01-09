package com.vslimit.kotlindemo.ui

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import com.vslimit.kotlindemo.extensions.ctx
import com.vslimit.kotlindemo.util.Const

/**
 * Created by vslimit on 17/1/6.
 */
interface DrawerLayoutManager {

    val drawerLayout: DrawerLayout

    fun selectDrawer(itemId: Int, selItemId: Int) {
        if (itemId != selItemId) {
            val intent = Intent(drawerLayout.ctx, Const.DRAWER_MAP[itemId])
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            drawerLayout.ctx.startActivity(intent)
        }
    }

    fun setupDrawerContent(navigationView: NavigationView, itemId: Int) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawer(menuItem.itemId, itemId)
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
    }
}