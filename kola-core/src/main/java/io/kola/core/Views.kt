package io.kola.core

import android.animation.Animator
import android.animation.ValueAnimator
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
    @Deprecated("No getter for this property", level = DeprecationLevel.ERROR)
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

fun View.expandVertically(animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    val height = this.height
    measure(View.MeasureSpec.makeMeasureSpec(this.width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    animateHeight(measuredHeight, from = height, animator = animator, autoStart = autoStart, removeListenersOnEnd = removeListenersOnEnd)
}

fun View.collapseVertically(animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    animateHeight(0, animator = animator, autoStart = autoStart, removeListenersOnEnd = removeListenersOnEnd)
}

fun View.expandHorizontally(animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    val width = this.width
    measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(this.height, View.MeasureSpec.EXACTLY))
    animateWidth(measuredWidth, from = width, animator = animator, autoStart = autoStart, removeListenersOnEnd = removeListenersOnEnd)
}

fun View.collapseHorizontally(animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    animateWidth(0, animator = animator, autoStart = autoStart, removeListenersOnEnd = removeListenersOnEnd)
}

fun View.animateHeight(to: Int, from: Int? = null, animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    if (this.layoutParams == null) return

    val realAnimator = animator ?: ValueAnimator.ofFloat(0f, 1f)

    val initialHeight = from ?: this.height

    layoutHeight = initialHeight

    val listener = object : Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(anim: ValueAnimator?) {
            val progress = realAnimator.animatedFraction
            layoutHeight = (initialHeight + progress * (to - initialHeight)).toInt()
        }

        override fun onAnimationStart(anim: Animator?) {}

        override fun onAnimationRepeat(anim: Animator?) {}

        override fun onAnimationCancel(anim: Animator?) {
            if (removeListenersOnEnd) {
                realAnimator.removeUpdateListener(this)
                realAnimator.removeListener(this)
            }
        }

        override fun onAnimationEnd(anim: Animator?) {
            if (removeListenersOnEnd) {
                realAnimator.removeUpdateListener(this)
                realAnimator.removeListener(this)
            }
        }
    }
    realAnimator.addUpdateListener(listener)
    realAnimator.addListener(listener)

    if (autoStart) realAnimator.start()
}

fun View.animateWidth(to: Int, from: Int? = null, animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    if (this.layoutParams == null) return

    val realAnimator = animator ?: ValueAnimator.ofFloat(0f, 1f)
    val initialWidth = from ?: this.width

    layoutWidth = initialWidth

    val listener = object : Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(anim: ValueAnimator?) {
            val progress = realAnimator.animatedFraction
            layoutWidth = (initialWidth + progress * (to - initialWidth)).toInt()
        }

        override fun onAnimationStart(anim: Animator?) {}

        override fun onAnimationRepeat(anim: Animator?) {}

        override fun onAnimationCancel(anim: Animator?) {
            if (removeListenersOnEnd) {
                realAnimator.removeUpdateListener(this)
                realAnimator.removeListener(this)
            }
        }

        override fun onAnimationEnd(anim: Animator?) {
            if (removeListenersOnEnd) {
                realAnimator.removeUpdateListener(this)
                realAnimator.removeListener(this)
            }
        }
    }
    realAnimator.addUpdateListener(listener)
    realAnimator.addListener(listener)

    if (autoStart) realAnimator.start()
}