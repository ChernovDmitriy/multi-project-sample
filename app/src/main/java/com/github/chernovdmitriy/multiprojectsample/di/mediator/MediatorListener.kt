package com.github.chernovdmitriy.multiprojectsample.di.mediator

import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature1.Feature1MediatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2.Feature2MediatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature3.Feature3MediatorComponent

object MediatorListener {

    fun listenFeatures() {
        Feature1ComponentProvider.injectionFunction = {
            Feature1MediatorComponent.newInstance().inject(this)
        }

        Feature2ComponentProvider.injectionFunction = {
            Feature2MediatorComponent.newInstance().inject(this)
        }

        Feature3ComponentProvider.injectionFunction = {
            Feature3MediatorComponent.newInstance().inject(this)
        }

    }

}