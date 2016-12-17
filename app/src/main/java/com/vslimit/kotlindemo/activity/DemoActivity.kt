package com.vslimit.kotlindemo.activity

import android.os.Bundle
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.model.FragmentEnum

class DemoActivity(override val layoutResourceId: Int = R.layout.activity_demo) : BaseActivity() {

    companion object {
        val NAME = "DemoActivity:name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val name = intent.getStringExtra(NAME)
            initFragment(FragmentEnum.valueOf(name).fragment)
        }
        initTitle("DemoActivity")
        initBack()
    }
}
