package io.kola.ui

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewManager
import io.kola.core.SupportFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView

abstract class UiComponent: AnkoComponent<Context> {
    lateinit var context: AnkoContext<Context> private set
    lateinit var view: View private set

    override fun createView(ui: AnkoContext<Context>): View {
        context = ui
        view = createView()
        onViewCreated()
        return view
    }

    abstract fun createView(): View

    protected open fun onViewCreated() { }
}

fun UiComponent.layout(block: AnkoContext<Context>.() -> View) = with(context, block)

fun UiComponent.createView(context: Context) = createView(AnkoContext.create(context))
fun UiComponent.createView(fragment: Fragment) = createView(AnkoContext.create(fragment.activity))
fun UiComponent.createView(fragment: SupportFragment) = createView(AnkoContext.create(fragment.context))

fun UiComponent.setContentView(activity: Activity) = createView(AnkoContext.create(activity, true))


fun <T: UiComponent> ViewManager.include(block: ()-> T) = include(block) {}
inline fun <T: UiComponent> ViewManager.include(block: () -> T, init: T.() -> Unit) = include(block(), init)

fun <T: UiComponent> ViewManager.include(component: T) = include(component) {}
inline fun <T: UiComponent> ViewManager.include(component: T, init: T.() -> Unit): T {
    ankoView({
        component.createView(it)
    }, 0, {
        init(component)
    })
    return component
}

fun createUi(block: AnkoContext<Context>.() -> View) = object: UiComponent() {
    override fun createView() = layout(block)
}