package com.vslimit.kotlindemo.fragment

import com.vslimit.kotlindemo.R

/**
 * Created by vslimit on 16/12/2.
 */
class ProductFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.fragment_product
    companion object {
        fun getInstance(): ProductFragment {
            return ProductFragment()
        }
    }
}