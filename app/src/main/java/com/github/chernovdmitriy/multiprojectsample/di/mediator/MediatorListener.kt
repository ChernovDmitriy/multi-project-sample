package com.github.chernovdmitriy.multiprojectsample.di.mediator

import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider

object MediatorListener {

    private val mediatorComponent by lazy { MediatorComponent.instance }

    fun listenFeatures() {
        Feature1ComponentProvider.provideComponentFunction = {
            mediatorComponent.inject(Feature1ComponentProvider.instance)
        }

        Feature2ComponentProvider.provideComponentFunction = {
            mediatorComponent.inject(Feature2ComponentProvider.instance)
        }

    }

}