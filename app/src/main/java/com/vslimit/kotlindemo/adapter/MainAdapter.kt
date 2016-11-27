package com.vslimit.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_main.view.*
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.extensions.ctx

/**
 * Created by vslimit on 16/1/15.
 */
class MainAdapter(val items: List<String>, val itemClick: (String) -> Unit) :
        RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.ctx.layoutInflater.inflate(R.layout.item_list_main, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View, val itemClick: (String) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(item: String) {
            with(item) {
                itemView.item_name.text = item
                itemView.item_text.text = item
                itemView.onClick { itemClick(item) }
            }
        }
    }
}