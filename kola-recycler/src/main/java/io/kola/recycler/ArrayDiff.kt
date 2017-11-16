package io.kola.recycler

import android.support.v7.util.DiffUtil

open class ArrayDiff<T>(private val old: Array<T>, private val new: Array<T>) : DiffUtil.Callback() {

    override final fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areItemsTheSame(old[oldItemPosition], new[newItemPosition])

    override final fun getOldListSize() = old.size

    override final fun getNewListSize() = new.size

    override final fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areContentsTheSame(old[oldItemPosition], new[newItemPosition])

    open fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    open fun areContentsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)

    fun calculateDiff(detectMoves: Boolean = true) = DiffUtil.calculateDiff(this, detectMoves)
}