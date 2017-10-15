package io.kola.core

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.View
import kotlin.coroutines.experimental.suspendCoroutine

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val View.isVisible get() = visibility == View.VISIBLE
val View.isGone get() = visibility == View.GONE
val View.isInvisible get() = visibility == View.INVISIBLE

fun View.anim() = ViewCompat.animate(this)

suspend fun ViewPropertyAnimatorCompat.startAsync() = suspendCoroutine<Unit> {
    withEndAction {
        it.resume(Unit)
    }.start()
}

fun View.elevate(elevation: Float) = ViewCompat.setElevation(this, elevation)
fun View.elevate(elevation: Int) = ViewCompat.setElevation(this, elevation.toFloat())

fun View.transparent() {
    alpha = 0f
}

fun View.opaque() {
    alpha = 1f
}

var View.scale: Float
    get() = noGetter()
    set(value) {
        scaleX = value
        scaleY = value
    }

var View.layoutHeight: Int
    get() = layoutParams.height
    set(value) {
        val layoutParams = this.layoutParams
        layoutParams.height = value
        this.layoutParams = layoutParams
    }

var View.layoutWidth: Int
    get() = layoutParams.width
    set(value) {
        val layoutParams = this.layoutParams
        layoutParams.width = value
        this.layoutParams = layoutParams
    }

fun View.layoutSize(width: Int, height: Int) {
    val layoutParams = this.layoutParams
    layoutParams.width = width
    layoutParams.height = height
    this.layoutParams = layoutParams
}