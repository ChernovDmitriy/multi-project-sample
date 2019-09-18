package com.github.chernovdmitriy.feature3_impl.di

import javax.inject.Inject

class Feature3ComponentProvider private constructor() {

    @Inject
    lateinit var feature3Component: Feature3Component


    companion object {
        private val instance = Feature3ComponentProvider()

        var injectionFunction: (Feature3ComponentProvider.(String) -> Unit)? = null

        fun getInstance(vrp: String): Feature3ComponentProvider {
            injectionFunction?.invoke(instance, vrp)
            return instance
        }
    }
}