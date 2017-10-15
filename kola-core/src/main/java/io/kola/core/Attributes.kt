package io.kola.core

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.support.annotation.*
import android.util.TypedValue

inline fun Context.attributes(vararg attrs: Int, block: TypedArray.() -> Unit) {
    val typedArray = obtainStyledAttributes(attrs)
    block(typedArray)
    typedArray.recycle()
}

fun Context.attribute(attr: Int): TypedValue {
    val typedValue = TypedValue()
    attributes(attr) {
        getValue(0, typedValue)
    }
    return typedValue
}

fun Context.booleanAttribute(@BoolRes attr: Int, default: Boolean): Boolean {
    var value = default
    attributes(attr) {
        value = getBoolean(0, default)
    }
    return value
}

fun Context.stringAttribute(@StringRes attr: Int): String? {
    var value: String? = null
    attributes(attr) {
        value = getString(0)
    }
    return value
}

fun Context.textAttribute(@StringRes attr: Int): CharSequence? {
    var value: CharSequence? = null
    attributes(attr) {
        value = getText(0)
    }
    return value
}

fun Context.textArrayAttribute(@ArrayRes attr: Int): Array<CharSequence?>? {
    var value: Array<CharSequence?>? = null
    attributes(attr) {
        value = this.getTextArray(0)
    }
    return value
}

fun Context.colorAttribute(@ColorRes attr: Int, default: Int): Int {
    var value = default
    attributes(attr) {
        value = getColor(0, default)
    }
    return value
}

fun Context.colorStateListAttribute(@ColorRes attr: Int): ColorStateList? {
    var value: ColorStateList? = null
    attributes(attr) {
        value = getColorStateList(0)
    }
    return value
}

fun Context.dimensionAttribute(@DimenRes attr: Int, default: Float): Float {
    var value = default
    attributes(attr) {
        value = getDimension(0, default)
    }
    return value
}

fun Context.dimensionPixelOffsetAttribute(@DimenRes attr: Int, default: Int): Int {
    var value = default
    attributes(attr) {
        value = getDimensionPixelOffset(0, default)
    }
    return value
}

fun Context.dimensionPixelSizeAttribute(@DimenRes attr: Int, default: Int): Int {
    var value = default
    attributes(attr) {
        value = getDimensionPixelSize(0, default)
    }
    return value
}

fun Context.intAttribute(@DimenRes attr: Int, default: Int): Int {
    var value = default
    attributes(attr) {
        value = getInt(0, default)
    }
    return value
}

fun Context.floatAttribute(@DimenRes attr: Int, default: Float): Float {
    var value = default
    attributes(attr) {
        value = getFloat(0, default)
    }
    return value
}

fun Context.drawableAttribute(@DrawableRes attr: Int): Drawable? {
    var value: Drawable? = null
    attributes(attr) {
        value = getDrawable(0)
    }
    return value
}