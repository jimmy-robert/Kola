package io.kola.core

import android.view.ViewGroup

private typealias Params = ViewGroup.LayoutParams

private val matchParent = Params.MATCH_PARENT
private val wrapContent = Params.WRAP_CONTENT

val ViewGroup.fullWidth get() = Params(matchParent, wrapContent)
val ViewGroup.fullHeight get() = Params(wrapContent, matchParent)
val ViewGroup.fullSize get() = Params(matchParent, matchParent)