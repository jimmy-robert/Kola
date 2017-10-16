package io.kola.recycler

import android.view.ViewGroup
import io.kola.ui.createView

infix fun <T: RecyclerItem> T.from(parent: ViewGroup) = apply {
    createView(parent.context)
}