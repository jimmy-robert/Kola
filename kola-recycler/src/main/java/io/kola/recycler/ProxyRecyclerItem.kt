package io.kola.recycler

import android.view.ViewGroup
import io.kola.ui.UiComponent

class ProxyRecyclerItem<out T : UiComponent>(parent: ViewGroup, private val base: T) : RecyclerItem(parent) {
    override fun createView() = base.createView(context)
    fun withBase(block: T.() -> Unit) = block(base)
}