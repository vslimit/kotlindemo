package com.vslimit.kotlindemo.model

import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.fragment.BaseFragment
import com.vslimit.kotlindemo.fragment.GankListFragment
import com.vslimit.kotlindemo.fragment.MainFragment

/**
 * Created by vslimit on 16/12/2.
 */
enum class TabTagEnum (val text: String, val res: Int, val selRes: Int, val fragment: BaseFragment) {
    HOME("首页", R.mipmap.ic_home, R.mipmap.ic_home_sel, MainFragment.getInstance()), GANK("干货", R.mipmap.ic_product, R.mipmap.ic_product_sel, GankListFragment.getInstance()), CART("购物车", R.mipmap.ic_cart, R.mipmap.ic_cart_sel, MainFragment.getInstance()), ME("我的", R.mipmap.ic_user, R.mipmap.ic_user_sel, MainFragment.getInstance())
}
