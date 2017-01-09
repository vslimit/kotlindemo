package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.activity.DemoActivity
import com.vslimit.kotlindemo.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_list_main.view.*
import org.jetbrains.anko.info
import org.jetbrains.anko.onClick
import org.jetbrains.anko.onLongClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

/**
 * Created by vslimit on 16/2/24.
 */
class MainFragment() : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_main
    var adapter: BaseAdapter<String>? = null
    var items = arrayListOf("VOLLEY_FRAGMENT", "RETROFIT_FRAGMENT")

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(act)
        demoRv.layoutManager = layoutManager
//        adapter = MainAdapter(items) {
////            (act as MainActivity).switchContent(this, itemsMap[it]!!)
//            act.startActivity<DemoActivity>(DemoActivity.NAME to it)
//        }

        adapter = BaseAdapter(R.layout.item_list_main, items) { view: View, item: String ->
            view.item_name.text = item
            view.item_text.text = item
            view.onClick {
                act.startActivity<DemoActivity>(DemoActivity.NAME to item)
            }
            view.onLongClick {
                itemLongClick(item)

            }
        }


        demoRv.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    private fun itemLongClick(item: String): Boolean {
        toast(item)
        return true
    }

    override fun onResume() {
        super.onResume()
        info("onResume")
    }

    override fun onStart() {
        super.onStart()
        info("onStart")
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

