package com.github.chernovdmitriy.multiprojectsample.di.mediator

import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider

object MediatorListener {

    private val mediatorComponent by lazy { MediatorComponent.instance }

    fun listenFeatures() {
        Feature1ComponentProvider.injectionFunction = {
            mediatorComponent.inject(this)
        }

        Feature2ComponentProvider.injectionFunction = {
            mediatorComponent.inject(this)
        }

        Feature3ComponentProvider.injectionFunction = {
            mediatorComponent.inject(this)
        }

    }

}