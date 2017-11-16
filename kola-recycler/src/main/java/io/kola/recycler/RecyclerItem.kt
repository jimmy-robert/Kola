package io.kola.recycler

import android.view.ViewGroup
import io.kola.ui.UiComponent
import io.kola.ui.createView

abstract class RecyclerItem(parent: ViewGroup) : UiComponent() {

    init {
        createView(parent.context)
    }

    lateinit var viewHolder: ItemViewHolder private set

    val adapterPosition get() = viewHolder.adapterPosition
    var isRecyclable
        get() = viewHolder.isRecyclable
        set(value) = viewHolder.setIsRecyclable(value)
    val itemId get() = viewHolder.itemId
    val itemViewType get() = viewHolder.itemViewType
    val layoutPosition get() = viewHolder.layoutPosition
    val oldPosition get() = viewHolder.oldPosition

    override fun onViewCreated() {
        super.onViewCreated()
        viewHolder = ItemViewHolder(this)
    }
}