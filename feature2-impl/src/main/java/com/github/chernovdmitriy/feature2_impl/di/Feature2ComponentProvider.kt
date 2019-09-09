package com.github.chernovdmitriy.feature2_impl.di

import javax.inject.Inject

class Feature2ComponentProvider {

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature2ComponentProvider.() -> Unit)? = null
//        val instance by lazy {
//            Feature2ComponentProvider().apply { injectionFunction?.invoke(this) }
//        }
    }

    @Inject
    lateinit var feature2Component: Feature2Component

}