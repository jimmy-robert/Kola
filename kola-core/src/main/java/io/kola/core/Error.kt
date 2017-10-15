package io.kola.core

fun noGetter(): Nothing = throw IllegalAccessException("No getter for this property")