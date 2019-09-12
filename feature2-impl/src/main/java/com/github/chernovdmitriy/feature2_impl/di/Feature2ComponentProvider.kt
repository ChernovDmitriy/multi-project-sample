package com.github.chernovdmitriy.feature2_impl.di

import javax.inject.Inject

class Feature2ComponentProvider private constructor() {

    @Inject
    lateinit var feature2Component: Feature2Component

    init {
        injectionFunction?.invoke(this)
    }

    companion object {
        var injectionFunction: (Feature2ComponentProvider.() -> Unit)? = null

        private val instance = Feature2ComponentProvider()

        fun getInstance(): Feature2ComponentProvider {
            injectionFunction?.invoke(instance)
            return instance
        }

    }

}