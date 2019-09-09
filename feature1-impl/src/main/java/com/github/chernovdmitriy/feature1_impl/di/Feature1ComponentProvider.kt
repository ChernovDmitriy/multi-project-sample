package com.github.chernovdmitriy.feature1_impl.di

import javax.inject.Inject

class Feature1ComponentProvider {

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature1ComponentProvider.() -> Unit)? = null

//        val instance = Feature1ComponentProvider().apply { injectionFunction?.invoke(this) }

//        val instance by lazy {
//            Feature1ComponentProvider().apply { injectionFunction?.invoke(this) }
//        }
    }

    @Inject
    lateinit var feature1Component: Feature1Component

}