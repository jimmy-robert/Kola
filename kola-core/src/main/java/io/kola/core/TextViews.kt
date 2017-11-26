package io.kola.core

import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView

var TextView.fontResource: Int
    @Deprecated("No getter for this property", level = DeprecationLevel.ERROR)
    get() = noGetter()
    set(value) {
        typeface = font(value)
    }

fun TextView.setMaxLinesSmoothly(maxLines: Int, animator: ValueAnimator? = null, autoStart: Boolean = animator == null, removeListenersOnEnd: Boolean = animator == null) {
    val height = this.height
    setMaxLines(maxLines)
    measure(View.MeasureSpec.makeMeasureSpec(this.width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    animateHeight(measuredHeight, from = height, animator = animator, autoStart = autoStart, removeListenersOnEnd = removeListenersOnEnd)
}
