package com.github.chernovdmitriy.feature2_impl.di

import javax.inject.Inject

class Feature2ComponentProvider {

    @Inject
    lateinit var feature2Component: Feature2Component

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature2ComponentProvider.() -> Unit)? = null
    }

}