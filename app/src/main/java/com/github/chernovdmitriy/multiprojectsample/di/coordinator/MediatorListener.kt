package com.github.chernovdmitriy.multiprojectsample.di.coordinator

import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1.Feature1CoordinatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2.Feature2CoordinatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3.Feature3CoordinatorComponent

object MediatorListener {

    fun listenFeatures() {
        Feature1ComponentProvider.injectionFunction = {
            Feature1CoordinatorComponent.newInstance().inject(this)
        }

        Feature2ComponentProvider.injectionFunction = {
            Feature2CoordinatorComponent.newInstance().inject(this)
        }

        Feature3ComponentProvider.injectionFunction = {
            Feature3CoordinatorComponent.newInstance().inject(this)
        }
    }
}