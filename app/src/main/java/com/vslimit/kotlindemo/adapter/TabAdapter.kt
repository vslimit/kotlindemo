package com.vslimit.kotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.vslimit.kotlindemo.R
import com.vslimit.kotlindemo.extensions.ctx
import com.vslimit.kotlindemo.model.TabTagEnum
import kotlinx.android.synthetic.main.tab_item_menu.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

/**
 * Created by vslimit on 16/12/2.
 */
class TabAdapter(val items: List<TabTagEnum>, val itemClick: (TabTagEnum) -> Unit) : RecyclerView.Adapter<TabAdapter.ViewHolder>() {

    var pos: Int = TabTagEnum.HOME.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.ctx.layoutInflater.inflate(R.layout.tab_item_menu, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position], pos == position)
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View, val itemClick: (TabTagEnum) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(item: TabTagEnum, flag: Boolean) {
            with(item) {
                itemView.item_image.imageResource = if (flag) item.selRes else item.res
                itemView.item_text.text = item.text
                itemView.onClick { itemClick(item) }
            }
        }
    }

}