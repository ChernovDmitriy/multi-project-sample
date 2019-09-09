package com.github.chernovdmitriy.feature1_impl.di

import javax.inject.Inject

class Feature1ComponentProvider private constructor() {

    companion object {

        var provideComponentFunction: (() -> Unit)? = null

        val instance by lazy {
            provideComponentFunction?.invoke()
            Feature1ComponentProvider()
        }
    }

    @Inject
    lateinit var feature1Component: Feature1Component

}