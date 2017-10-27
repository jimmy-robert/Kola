package io.kola.core

import android.support.annotation.IdRes
import android.view.View
import android.widget.RelativeLayout.*

/**
 * Place the current View to the start of [view].
 */
fun LayoutParams.startOf(view: View) {
    val id = view.id
    if (id == View.NO_ID) error("Id is not set for $view")
    addRule(START_OF, id)
}

/**
 * Place the current View to the end of [view].
 */
fun LayoutParams.endOf(view: View) {
    val id = view.id
    if (id == View.NO_ID) error("Id is not set for $view")
    addRule(END_OF, id)
}

/**
 * Set the current View start attribute the same as for [view].
 */
fun LayoutParams.sameStart(view: View) {
    val id = view.id
    if (id == View.NO_ID) error("Id is not set for $view")
    addRule(ALIGN_START, id)
}

/**
 * Set the current View end attribute the same as for [view].
 */
fun LayoutParams.sameEnd(view: View) {
    val id = view.id
    if (id == View.NO_ID) error("Id is not set for $view")
    addRule(ALIGN_END, id)
}

/**
 * Place the current View to the start of the View with a given [id].
 */
fun LayoutParams.startOf(@IdRes id: Int) = addRule(START_OF, id)

/**
 * Place the current View to the end of the View with a given [id].
 */
fun LayoutParams.endOf(@IdRes id: Int) = addRule(END_OF, id)

/**
 * Set the current View start attribute the same as for View with a given [id].
 */
fun LayoutParams.sameStart(@IdRes id: Int) = addRule(ALIGN_START, id)

/**
 * Set the current View end attribute the same as for View with a given [id].
 */
fun LayoutParams.sameEnd(@IdRes id: Int) = addRule(ALIGN_END, id)