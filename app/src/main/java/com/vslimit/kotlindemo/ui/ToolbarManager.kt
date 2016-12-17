package com.vslimit.kotlindemo.ui

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.Toolbar
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.activity.MainActivity
import com.vslimit.kotlindemo.extensions.ctx
import org.jetbrains.anko.appcompat.v7.onMenuItemClick
import org.jetbrains.anko.*


interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

//    fun enableMenu(id: Int = R.menu.menu_home, up: () -> Unit) {
//        toolbar.inflateMenu(id)
//        toolbar.onMenuItemClick {
//            when (it!!.itemId) {
//                R.id.index -> toolbar.ctx.startActivity<MainActivity>()
//                else -> up()
//            }
//            true
//        }
//    }

//    fun hiddenMenu(redId: Int) {
//        toolbar.menu.findItem(redId).isVisible = false
//    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

}