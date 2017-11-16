package io.kola.arch

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

inline fun <reified VM: ViewModel> FragmentActivity.viewModel() =
        ViewModelProviders.of(this).get(VM::class.java)

inline fun <reified VM: ViewModel> FragmentActivity.viewModel(key: String) =
        ViewModelProviders.of(this).get(key, VM::class.java)

inline fun <reified VM: ViewModel> Fragment.viewModel() =
        ViewModelProviders.of(this).get(VM::class.java)

inline fun <reified VM: ViewModel> Fragment.viewModel(key: String) =
        ViewModelProviders.of(this).get(key, VM::class.java)