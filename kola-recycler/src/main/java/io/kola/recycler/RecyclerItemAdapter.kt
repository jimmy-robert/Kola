package io.kola.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class RecyclerItemAdapter<T : RecyclerItem> : RecyclerView.Adapter<ItemViewHolder>() {

    @Suppress("UNCHECKED_CAST")
    private val ItemViewHolder?.asItem
        get() = this!!.item as T

    final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return onCreateItem(parent!!, viewType).viewHolder
    }

    final override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        onBindItem(holder.asItem, position)
    }

    final override fun onBindViewHolder(holder: ItemViewHolder?, position: Int, payloads: MutableList<Any>?) {
        onBindItem(holder.asItem, position, payloads)
    }

    final override fun onFailedToRecycleView(holder: ItemViewHolder?): Boolean {
        return onFailedToRecycleItem(holder.asItem)
    }

    final override fun onViewAttachedToWindow(holder: ItemViewHolder?) {
        super.onViewAttachedToWindow(holder)
        onItemAttachedToWindow(holder.asItem)
    }

    final override fun onViewDetachedFromWindow(holder: ItemViewHolder?) {
        super.onViewDetachedFromWindow(holder)
        onItemDetachedFromWindow(holder.asItem)
    }

    final override fun onViewRecycled(holder: ItemViewHolder?) {
        super.onViewRecycled(holder)
        onItemRecycled(holder.asItem)
    }

    abstract fun onCreateItem(parent: ViewGroup, viewType: Int): T

    abstract fun onBindItem(item: T, position: Int)

    open fun onBindItem(item: T, position: Int, payloads: MutableList<Any>?) {
        onBindItem(item, position)
    }

    open fun onFailedToRecycleItem(item: T): Boolean {
        return false
    }

    open fun onItemAttachedToWindow(item: T) {}

    open fun onItemDetachedFromWindow(item: T) {}

    open fun onItemRecycled(item: T) {}
}