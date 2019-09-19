package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2

import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_impl.api.Feature1Api
import com.github.chernovdmitriy.feature2_impl.api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.api.Feature2Output
import com.github.chernovdmitriy.feature2_impl.Feature2Coordinator
import com.github.chernovdmitriy.feature2_impl.di.DaggerFeature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Dependencies
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides

@Module
class Feature2CoordinatorModule {

    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Coordinator(
        mainCoordinator: MainCoordinator
    ): Feature2Coordinator {
        return InjectionCoordinatorHolder.instance.findCoordinator(
            Feature2Coordinator::class.java,
            { Feature2Coordinator(mainCoordinator) })
    }


    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Deps(
        coreObjectApi: CoreObjectApi,
        feature2Coordinator: Feature2Coordinator
    ): Feature2Dependencies {
        return object : Feature2Dependencies {
            override val feature2Output: Feature2Output = feature2Coordinator
            override val coreObjectApi: CoreObjectApi = coreObjectApi
        }
    }

    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Component(
        feature2Dependencies: Feature2Dependencies
    ): Feature2Component {
        return DaggerFeature2Component
            .builder()
            .feature2Dependencies(feature2Dependencies)
            .build()
            .also {
                InjectionHolderX.instance.addOwnerlessComponent(it)
            }
    }

    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Api(
        feature2Component: Feature2Component
    ): Feature2Api =
        feature2Component

}