package io.kola.core

import android.os.Build

val sdkInt get() =  Build.VERSION.SDK_INT

val jellyBean get() =  Build.VERSION_CODES.JELLY_BEAN
val kitKat get() =  Build.VERSION_CODES.KITKAT
val lollipop get() =  Build.VERSION_CODES.LOLLIPOP
val marshmallow get() =  Build.VERSION_CODES.M
val nougat get() =  Build.VERSION_CODES.N
val oreo get() =  Build.VERSION_CODES.O