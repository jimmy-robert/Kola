package io.kola.recycler

import android.view.ViewGroup

infix fun <T: RecyclerItem> T.from(parent: ViewGroup) = apply {
    createView(parent.context)
}