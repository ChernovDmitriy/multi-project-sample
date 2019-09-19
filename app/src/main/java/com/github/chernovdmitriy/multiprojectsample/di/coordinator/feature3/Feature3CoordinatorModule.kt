package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3

import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature3_impl.api.Feature3Api
import com.github.chernovdmitriy.feature3_impl.api.Feature3Output
import com.github.chernovdmitriy.feature3_impl.Feature3Coordinator
import com.github.chernovdmitriy.feature3_impl.di.DaggerFeature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Dependencies
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides

@Module
class Feature3CoordinatorModule(private val vrpNumber: String) {


    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Coordinator(
        mainCoordinator: MainCoordinator
    ): Feature3Coordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(Feature3Coordinator::class.java,
            { Feature3Coordinator(mainCoordinator) })
    }


    @Provides
    @Feature3CoordinatorScope
    fun provideFeature3Deps(
        coreObjectApi: CoreObjectApi,
        feature3Coordinator: Feature3Coordinator
    ): Feature3Dependencies {
        return object : Feature3Dependencies {
            override val feature3Output: Feature3Output = feature3Coordinator
            override val coreObjectApi: CoreObjectApi = coreObjectApi
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