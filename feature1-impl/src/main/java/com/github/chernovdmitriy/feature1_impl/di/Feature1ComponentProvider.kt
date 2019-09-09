package com.github.chernovdmitriy.feature1_impl.di

import javax.inject.Inject

class Feature1ComponentProvider {

    @Inject
    lateinit var feature1Component: Feature1Component

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature1ComponentProvider.() -> Unit)? = null
    }


}