package com.vslimit.kotlindemo.mvp.users

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.adapter.BaseAdapter
import com.vslimit.kotlindemo.extensions.loading
import com.vslimit.kotlindemo.fragment.BaseFragment
import com.vslimit.kotlindemo.mvp.data.User
import com.vslimit.kotlindemo.util.Const
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.item_list_user.view.*
import org.jetbrains.anko.info
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast

/**
 * Created by sky on 17/1/10.
 */
class UsersFragment : BaseFragment(), UsersContract.View {
    override val layoutResourceId: Int = R.layout.fragment_users

    var mPresenter: UsersContract.Presenter? = null
    var adapter: BaseAdapter<User>? = null

    companion object {
        fun getInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(act)
        userListRv.layoutManager = layoutManager
        adapter = BaseAdapter(R.layout.item_list_user, arrayListOf()) { view: View, item: User ->
            view.item_user_name.text = item.name
            view.onClick {
                showTip("password:${item.password}")
            }
        }
        userListRv.adapter = adapter

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

    override fun setPresenter(presenter: UsersContract.Presenter) {
        mPresenter = checkNotNull(presenter)
        info("demo")
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    override fun showUsers(users: List<User>) {
        adapter!!.items = users
        adapter!!.notifyDataSetChanged()
    }

}