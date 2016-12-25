package com.vslimit.kotlindemo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.ui.LoadingDialog
import com.vslimit.kotlindemo.ui.ToolbarManager
import com.vslimit.kotlindemo.util.Const
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

/**
 * Created by vslimit on 16/1/12.
 */
abstract class BaseActivity : AppCompatActivity(), ToolbarManager, AnkoLogger {

    abstract val layoutResourceId: Int

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    var loadingDialog: LoadingDialog? = null

    open var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            //定义一个Handler，用于处理下载线程与UI间通讯
            if (!Thread.currentThread().isInterrupted) {
                when (msg.what) {
                    Const.SHOW -> loadingDialog!!.show()//显示进度对话框
                    Const.HIDE -> loadingDialog!!.hide()//隐藏进度对话框，不可使用dismiss()、cancel(),否则再次调用show()时，显示的对话框小圆圈不会动。
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        loadingDialog = LoadingDialog(this)
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
        loadingDialog!!.dismiss()
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