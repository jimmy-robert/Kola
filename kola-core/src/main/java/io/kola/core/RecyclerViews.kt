package io.kola.core

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.asList() {
    layoutManager = LinearLayoutManager(context)
}

fun RecyclerView.asGrid(columnCount: Int) {
    layoutManager = GridLayoutManager(context, columnCount)
}