package io.kola.recycler

import io.kola.ui.UiComponent

class ProxyRecyclerItem<out T: UiComponent>(private val base: T): RecyclerItem() {
    override fun createView() = base.createView(context)
    fun withBase(block: T.() -> Unit) = block(base)
}