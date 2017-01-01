package com.vslimit.kotlindemo.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.vslimit.kotlindemo.App
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.adapter.BaseAdapter
import com.vslimit.kotlindemo.event.BaseEvent
import com.vslimit.kotlindemo.extensions.loading
import com.vslimit.kotlindemo.model.Gank
import com.vslimit.kotlindemo.model.GankListResult
import com.vslimit.kotlindemo.util.Bus
import com.vslimit.kotlindemo.util.Const
import com.vslimit.kotlindemo.util.NetworkUtil
import com.vslimit.kotlindemo.util.net.volley.Listener
import com.vslimit.kotlindemo.util.net.volley.add
import com.vslimit.kotlindemo.util.net.volley.toString
import kotlinx.android.synthetic.main.fragment_ganks.*
import kotlinx.android.synthetic.main.item_list_gank.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.onLongClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

/**
 * Created by vslimit on 16/12/31.
 */
class GankListFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_ganks
    var result: GankListResult? = null
    var adapter: BaseAdapter<Gank>? = null

    companion object {
        fun getInstance(): GankListFragment {
            return GankListFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(act)
        gankListRv.layoutManager = layoutManager
    }

    private fun itemLongClick(item: Gank): Boolean {
        toast(item.desc)
        return true
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    fun init() {
        if (NetworkUtil.isNetwork(act)) {
            val listener = Listener<GankListResult> { e, r ->
                e?.let { Bus.post(BaseEvent<GankListResult>(error = e)) }
                r?.let { Bus.post(BaseEvent(response = r)) }
            }
            val url = resources.getString(R.string.gank_list)
            Log.d("Url:::", url)
            App.queue!!.add(listener, url)
            loading(Const.SHOW)
        } else {
            alert("网络错误", "网络未连接，请检查网络")
        }
    }

   fun onEventMainThread(event: BaseEvent<GankListResult>) {
        loading(Const.HIDE)
        val error = event.error
        result = event.response
        if (error != null) {
            toast(error.toString(resources))
        } else {
            if (result!!.error) {
                alert("网络错误", "网络未连接，请检查网络")
            } else {
                adapter = BaseAdapter(R.layout.item_list_gank, result!!.results) { view: View, item: Gank ->
                    view.item_gank_title.text = item.desc
                    view.item_gank_who.text = item.who
                    view.item_gank_date.text = item.publishedAt
                    view.onClick {
                        toast("onclick")
                    }
                    view.onLongClick {
                        itemLongClick(item)
                    }
                }
                gankListRv.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }


}