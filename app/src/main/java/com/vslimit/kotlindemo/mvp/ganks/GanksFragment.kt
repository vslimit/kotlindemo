package com.vslimit.kotlindemo.mvp.ganks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.adapter.BaseAdapter
import com.vslimit.kotlindemo.extensions.loading
import com.vslimit.kotlindemo.fragment.BaseFragment
import com.vslimit.kotlindemo.model.Gank
import com.vslimit.kotlindemo.util.Const
import kotlinx.android.synthetic.main.fragment_ganks.*
import kotlinx.android.synthetic.main.item_list_gank.view.*
import org.jetbrains.anko.info
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

/**
 * Created by sky on 17/1/10.
 */
class GanksFragment : BaseFragment(), GanksContract.View {
    override val layoutResourceId: Int = R.layout.fragment_ganks

    var mPresenter: GanksContract.Presenter? = null
    var adapter: BaseAdapter<Gank>? = null

    companion object {
        fun getInstance(): GanksFragment {
            return GanksFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(act)
        gankListRv.layoutManager = layoutManager
        adapter = BaseAdapter(R.layout.item_list_gank, arrayListOf()) { view: View, item: Gank ->
            view.item_gank_title.text = item.desc
            view.item_gank_who.text = item.who
            view.item_gank_date.text = item.publishedAt
            view.onClick {
                showTip("onclick")
            }

        }
        gankListRv.adapter = adapter

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun hideLoading() {
        loading(Const.HIDE)
    }

    override fun showLoading() {
        loading(Const.SHOW)

    }

    override fun showTip(message: String) {
        toast(message)
    }

    override fun setPresenter(presenter: GanksContract.Presenter) {
        mPresenter = checkNotNull(presenter)
        info("demo")
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    override fun showGanks(ganks: List<Gank>) {
        adapter!!.items = ganks
        adapter!!.notifyDataSetChanged()
    }

}