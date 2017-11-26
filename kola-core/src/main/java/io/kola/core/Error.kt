package io.kola.core

fun noGetter(propertyName: String = "this property"): Nothing = throw IllegalAccessException("No getter for $propertyName")

fun unreachable(): Nothing = throw IllegalAccessException("Unreachable code")