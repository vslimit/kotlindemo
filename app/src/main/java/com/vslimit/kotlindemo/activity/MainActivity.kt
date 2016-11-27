package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment(MainFragment.getInstance())
    }

    override fun onResume() {
        super.onResume()
    }

    fun switchContent(from: Fragment, to: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
        val ft: FragmentTransaction = fm.beginTransaction()
        if (!to.isAdded) {
            // 先判断是否被add过
            ft.hide(from).add(R.id.frameLayout, to).commit() // 隐藏当前的fragment，add下一个到Activity中
        } else {
            ft.hide(from).show(to).commit() // 隐藏当前的fragment，显示下一个
        }
    }

    fun initFragment(to: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        //添加渐隐渐现的动画
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.frameLayout, to).commit()
    }
}
