package io.kola.ratiolayout

import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.ratioLayout(ratio: Float, offset: Int = 0, init: RatioLayout.() -> Unit): RatioLayout {
    return ankoView({
        RatioLayout(it).apply {
            this.ratio = ratio
            this.offset = offset
        }
    }, 0, init)
}

inline fun ViewManager.squareLayout(init: RatioLayout.() -> Unit) = ratioLayout(1f, init = init)
inline fun ViewManager.cinemaLayout(init: RatioLayout.() -> Unit) = ratioLayout(16f / 9, init = init)
inline fun ViewManager.posterLayout(init: RatioLayout.() -> Unit) = ratioLayout(2f / 3, init = init)