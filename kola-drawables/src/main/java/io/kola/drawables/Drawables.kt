package io.kola.drawables

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import io.kola.core.drawableAttribute

fun ovalDrawable(fillColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0, strokeColor: Int = Color.TRANSPARENT): Drawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.OVAL
        setColor(fillColor)
        setStroke(strokeWidth, strokeColor)
    }
}

fun ringDrawable(fillColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0, strokeColor: Int = Color.TRANSPARENT): Drawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RING
        setColor(fillColor)
        setStroke(strokeWidth, strokeColor)
    }
}

fun rectangleDrawable(fillColor: Int = Color.TRANSPARENT, strokeWidth: Int = 0, strokeColor: Int = Color.TRANSPARENT, cornerRadius: Float = 0f): Drawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(fillColor)
        setStroke(strokeWidth, strokeColor)
        this.cornerRadius = cornerRadius
    }
}

val Context.selectableItemBackground
    get() = drawableAttribute(android.R.attr.selectableItemBackground)

val Context.selectableItemBackgroundBorderless
    get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawableAttribute(android.R.attr.selectableItemBackground)
    } else {
        drawableAttribute(android.R.attr.selectableItemBackgroundBorderless)
    }

val Context.actionBarItemBackground
    get() = drawableAttribute(android.R.attr.actionBarItemBackground)

val View.selectableItemBackground get() = context.selectableItemBackground

val View.selectableItemBackgroundBorderless get() = context.selectableItemBackgroundBorderless

val View.actionBarItemBackground get() = context.actionBarItemBackground

fun View.selectable() {
    isClickable = true
    background = selectableItemBackground
}

fun View.selectableBorderless() {
    isClickable = true
    background = selectableItemBackgroundBorderless
}

fun View.selectableAsActionBarItem() {
    isClickable = true
    background = actionBarItemBackground
}