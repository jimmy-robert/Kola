package io.kola.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, callback: (T?) -> Unit) {
    observe(lifecycleOwner, Observer<T> { callback(it) })
}

fun <T> liveData() = MutableLiveData<T>()