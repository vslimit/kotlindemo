package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.model.TabTagEnum
import com.vslimit.kotlindemo.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {

    internal var items = TabTagEnum.values().asList()

    var adapter: TabAdapter? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initFragment(TabTagEnum.HOME.fragment)
        }
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
}
