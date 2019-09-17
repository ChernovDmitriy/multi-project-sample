package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Api
import com.github.chernovdmitriy.feature3_api.Feature3Output
import com.github.chernovdmitriy.feature3_impl.di.DaggerFeature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Dependencies
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides

@Module
class Feature3CoordinatorModule(private val vrpNumber: String) {


    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Coordinator(
        mainCoordinator: MainCoordinator,
        coordinatorManager: CoordinatorManager
    ): Feature3Coordinator {
        val coordinator = Feature3Coordinator(mainCoordinator)
        coordinatorManager.addCoordinator(Feature3Coordinator.KEY, coordinator)
        return coordinator
    }


    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Deps(
        feature2Api: Feature2Api,
        feature3Coordinator: Feature3Coordinator
    ): Feature3Dependencies {
        return object : Feature3Dependencies {
            override val feature3Output: Feature3Output = feature3Coordinator
            override val feature2Api: Feature2Api = feature2Api
            override val vrp: String = vrpNumber
        }
    }

    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Component(
        feature3Dependencies: Feature3Dependencies
    ): Feature3Component {
        return DaggerFeature3Component
            .builder()
            .feature3Dependencies(feature3Dependencies)
            .build()
            .also {
                InjectionHolderX.instance.addOwnerlessComponent(it)
            }
    }


    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Api(
        feature3Component: Feature3Component
    ): Feature3Api =
        feature3Component

}