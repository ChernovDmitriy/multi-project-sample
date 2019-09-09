package com.github.chernovdmitriy.feature2_impl.di

import javax.inject.Inject

class Feature2ComponentProvider private constructor() {

    companion object {

        var provideComponentFunction: (() -> Unit)? = null

        val instance by lazy {
            provideComponentFunction?.invoke()
            Feature2ComponentProvider()
        }
    }

    @Inject
    lateinit var feature2Component: Feature2Component

}