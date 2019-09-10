package com.github.chernovdmitriy.feature3_impl.di

import javax.inject.Inject

class Feature3ComponentProvider {

    @Inject
    lateinit var feature3Component: Feature3Component

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature3ComponentProvider.() -> Unit)? = null
    }

}