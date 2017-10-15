package io.kola.ui

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewManager
import io.kola.core.SupportFragment
import org.jetbrains.anko.UI
import org.jetbrains.anko.custom.ankoView

abstract class UiComponent {

    lateinit var context: Context private set
    lateinit var view: View private set

    abstract fun createView(): View

    fun createView(context: Context): View {
        this.context = context
        view = createView()
        onViewCreated()
        return view
    }

    fun asContentView(activity: Activity) {
        createView(activity)
        activity.setContentView(view)
    }

    fun createView(fragment: Fragment) = createView(fragment.activity)

    fun createView(fragment: SupportFragment) = createView(fragment.context)

    protected inline fun layout(block: AnkoBuilder<Context>) = context.UI(block).view

    protected open fun onViewCreated() { }
}

fun <T: UiComponent> ViewManager.include(component: T) = include(component) {}
inline fun <T: UiComponent> ViewManager.include(component: T, init: T.() -> Unit): T {
    ankoView({
        component.createView(it)
    }, 0, {
        init(component)
    })
    return component
}

fun ui(block: AnkoBuilder<Context>) = object: UiComponent() {
    override fun createView() = layout(block)
}

fun <T: UiComponent> ViewManager.include(block: ()-> T) = include(block) {}
inline fun <T: UiComponent> ViewManager.include(block: () -> T, init: T.() -> Unit) = include(block(), init)