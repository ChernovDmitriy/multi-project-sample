package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1

import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_api.Feature1Output
import com.github.chernovdmitriy.feature1_impl.Feature1Coordinator
import com.github.chernovdmitriy.feature1_impl.di.Feature1Dependencies
import com.github.chernovdmitriy.feature1_impl.di.DaggerFeature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides

@Module
class Feature1CoordinatorModule {

    @Provides
    @Feature1CoordinatorScope
    fun provideFeature1Coordinator(
        mainCoordinator: MainCoordinator
    ): Feature1Coordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(
            Feature1Coordinator::class.java,
            { Feature1Coordinator(mainCoordinator) })
    }

    @Provides
    @Feature1CoordinatorScope
    fun provideFeature1Deps(
        coreObjectApi: CoreObjectApi,
        feature1Coordinator: Feature1Coordinator
    ): Feature1Dependencies {
        return object : Feature1Dependencies {
            override val feature1Output: Feature1Output = feature1Coordinator
            override val coreObjectApi: CoreObjectApi = coreObjectApi
        }
    }

    @Provides
    @Feature1CoordinatorScope
    fun provideFeature1Component(
        feature1Dependencies: Feature1Dependencies
    ): Feature1Component {
        return DaggerFeature1Component
            .builder()
            .feature1Dependencies(feature1Dependencies)
            .build()
            .also {
                InjectionHolderX.instance.addOwnerlessComponent(it)
            }
    }

    @Provides
    @Feature1CoordinatorScope
    fun provideFeature1Api(
        feature1Component: Feature1Component
    ): Feature1Api =
        feature1Component

}