package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.ui.ToolbarManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by vslimit on 16/1/12.
 */
abstract class BaseActivity : AppCompatActivity(), ToolbarManager, AnkoLogger {

    abstract val layoutResourceId: Int

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onStop() {
        super.onStop()

    }

    fun switchContent(from: Fragment, to: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
        val ft: FragmentTransaction = fm.beginTransaction()
        if (!to.isAdded) {
            // 先判断是否被add过
            ft.hide(from).add(R.id.frameLayout, to) // 隐藏当前的fragment，add下一个到Activity中
        } else {
            ft.hide(from).show(to) // 隐藏当前的fragment，显示下一个
        }
        ft.commit()
    }

    fun initFragment(to: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
//        val ft: FragmentTransaction = fm.beginTransaction()
        fm.beginTransaction().add(R.id.frameLayout, to).commit()
    }

    open fun initTitle(title: String = "Kotlin Demo") {
        toolbarTitle = title
    }

    open fun initBack() {
        enableHomeAsUp { onBackPressed() }
    }

}