package com.vslimit.kotlindemo.model

import com.vslimit.kotlindemo.fragment.BaseFragment
import com.vslimit.kotlindemo.fragment.RetrofitFragment
import com.vslimit.kotlindemo.fragment.VolleyFragment

/**
 * Created by vslimit on 16/12/16.
 */
enum class FragmentEnum(val fragment: BaseFragment) {
    VOLLEY_FRAGMENT(VolleyFragment.getInstance()), RETROFIT_FRAGMENT(RetrofitFragment.getInstance()),
}