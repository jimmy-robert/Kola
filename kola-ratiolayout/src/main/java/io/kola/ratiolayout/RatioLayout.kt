package io.kola.ratiolayout

import android.content.Context
import android.view.View
import org.jetbrains.anko._FrameLayout

class RatioLayout(ctx: Context) : _FrameLayout(ctx) {

    var offset: Int = 0
    var ratio: Float = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ratio == 0f) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        val height = heightMeasureSpec.asSize
        val width = widthMeasureSpec.asSize
        when {
            height == 0 -> {
                val size = width / ratio - offset
                val spec = heightMeasureSpec.withSize(size)
                super.onMeasure(widthMeasureSpec, spec)
            }
            width == 0 -> {
                val size = height * ratio - offset
                val spec = widthMeasureSpec.withSize(size)
                super.onMeasure(spec, heightMeasureSpec)
            }
            else -> super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    private val Int.asSize get() = View.MeasureSpec.getSize(this)

    private fun Int.withSize(size: Float) = View.MeasureSpec.makeMeasureSpec(size.toInt(), View.MeasureSpec.getMode(this))
}