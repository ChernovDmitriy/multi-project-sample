package com.github.chernovdmitriy.feature1_impl.di

import javax.inject.Inject

class Feature1ComponentProvider private constructor() {

    @Inject
    lateinit var feature1Component: Feature1Component

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature1ComponentProvider.() -> Unit)? = null

        private val instance = Feature1ComponentProvider()

        fun getInstance(): Feature1ComponentProvider {
            injectionFunction?.invoke(instance)
            return instance
        }

    }


}