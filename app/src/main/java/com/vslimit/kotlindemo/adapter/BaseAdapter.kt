package com.vslimit.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.vslimit.kotlindemo.extensions.ctx
import org.jetbrains.anko.layoutInflater

/**
 * Created by vslimit on 16/1/15.
 */
class BaseAdapter<T>(val layoutResourceId: Int, val items: List<T>, val init: (View, T) -> Unit) :
        RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = parent.ctx.layoutInflater.inflate(layoutResourceId, parent, false)
        return ViewHolder(view, init)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindForecast(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder<in T>(view: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(item: T) {
            with(item) {
                init(itemView, item)
            }
        }
    }
}