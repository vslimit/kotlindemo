package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.activity.MainActivity
import com.vslimit.kotlindemo.adapter.MainAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.act


/**
 * Created by vslimit on 16/2/24.
 */
class MainFragment() : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_main
    var adapter: MainAdapter? = null
    var items = arrayListOf("volley demo","retrofit demo")

    var itemsMap = hashMapOf<String, Fragment>()
    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsMap.put("volley demo", VolleyFragment.getInstance())
        itemsMap.put("retrofit demo", RetrofitFragment.getInstance())
        val layoutManager: LinearLayoutManager = LinearLayoutManager(act)
        demoRv.layoutManager = layoutManager
        adapter = MainAdapter(items) {
            (act as MainActivity).switchContent(this, itemsMap[it]!!)
        }
        demoRv.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}