package io.kola.core

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.FontRes
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.View

fun Context.color(@ColorRes id: Int) = ResourcesCompat.getColor(resources, id, theme)
fun View.color(@ColorRes id: Int) = context.color(id)

fun Context.colorStateList(@ColorRes id: Int) = ResourcesCompat.getColorStateList(resources, id, theme)
fun View.colorStateList(@ColorRes id: Int) = context.colorStateList(id)

fun Context.font(@FontRes id: Int) = ResourcesCompat.getFont(this, id)
fun View.font(@FontRes id: Int) = context.font(id)

fun Context.drawable(@ColorRes id: Int) = ResourcesCompat.getDrawable(resources, id, theme)
fun View.drawable(@ColorRes id: Int) = context.drawable(id)

fun Context.vector(@DrawableRes id: Int) = VectorDrawableCompat.create(resources, id, theme)
fun View.vector(@DrawableRes id: Int) = context.vector(id)
