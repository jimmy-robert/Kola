package io.kola.core

import android.support.v4.content.res.ResourcesCompat
import android.widget.TextView

fun TextView.allCaps() = setAllCaps(true)

var TextView.textColorResource: Int
    get() = noGetter()
    set(value) {
        setTextColor(ResourcesCompat.getColor(context.resources, value, context.theme))
    }

var TextView.fontResource: Int
    get() = noGetter()
    set(value) {
        typeface = font(value)
    }