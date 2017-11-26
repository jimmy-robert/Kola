package io.kola.core

import android.widget.ImageView

var ImageView.tint: Int
    @Deprecated("No getter for this property", level = DeprecationLevel.ERROR)
    get() = noGetter()
    set(value) = setColorFilter(value)

var ImageView.tintResource: Int
    @Deprecated("No getter for this property", level = DeprecationLevel.ERROR)
    get() = noGetter()
    set(value) {
        tint = color(value)
    }

fun ImageView.centerCrop() {
    scaleType = ImageView.ScaleType.CENTER_CROP
}

fun ImageView.center() {
    scaleType = ImageView.ScaleType.CENTER
}

fun ImageView.centerInside() {
    scaleType = ImageView.ScaleType.CENTER_INSIDE
}

fun ImageView.fitCenter() {
    scaleType = ImageView.ScaleType.FIT_CENTER
}

fun ImageView.fitStart() {
    scaleType = ImageView.ScaleType.FIT_START
}

fun ImageView.fitEnd() {
    scaleType = ImageView.ScaleType.FIT_END
}

fun ImageView.fitXY() {
    scaleType = ImageView.ScaleType.FIT_XY
}

fun ImageView.matrix() {
    scaleType = ImageView.ScaleType.MATRIX
}