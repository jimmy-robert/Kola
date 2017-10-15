package io.kola.core

import android.os.Build
import android.view.View

fun View.selectable() {
    isClickable = true
    background = context.drawableAttribute(android.R.attr.selectableItemBackground)
}

fun View.selectableBorderless() {
    isClickable = true
    background = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        context.drawableAttribute(android.R.attr.selectableItemBackground)
    } else {
        context.drawableAttribute(android.R.attr.selectableItemBackgroundBorderless)
    }
}

fun View.selectableAsActionBarItem() {
    isClickable = true
    background = context.drawableAttribute(android.R.attr.actionBarItemBackground)
}