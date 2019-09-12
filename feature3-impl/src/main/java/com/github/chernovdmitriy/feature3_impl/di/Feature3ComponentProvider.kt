package com.github.chernovdmitriy.feature3_impl.di

import javax.inject.Inject

class Feature3ComponentProvider private constructor() {

    @Inject
    lateinit var feature3Component: Feature3Component

//    init {
//        injectionFunction?.invoke(this)
//    }

    companion object {
        private val instance = Feature3ComponentProvider()

        var injectionFunction: (Feature3ComponentProvider.() -> Unit)? = null

        fun getInstance(): Feature3ComponentProvider {
            injectionFunction?.invoke(instance)
            return instance
        }

    }

}