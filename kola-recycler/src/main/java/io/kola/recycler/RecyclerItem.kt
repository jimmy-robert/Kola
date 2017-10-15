package io.kola.recycler

import io.kola.ui.UiComponent

abstract class RecyclerItem: UiComponent() {

    lateinit var viewHolder: ItemViewHolder private set

    override fun onViewCreated() {
        super.onViewCreated()
        viewHolder = ItemViewHolder(this)
    }
}