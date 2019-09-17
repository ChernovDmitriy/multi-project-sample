package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_api.Feature2Output
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Dependencies
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides

@Module
class Feature2CoordinatorModule {

    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Coordinator(mainCoordinator: MainCoordinator, coordinatorManager: CoordinatorManager): Feature2Coordinator {
        val coordinator = Feature2Coordinator(mainCoordinator)
        coordinatorManager.addCoordinator(Feature2Coordinator.KEY, coordinator)
        return coordinator
    }


    @Provides
    @Feature2CoordinatorScope
    fun provideFeature2Deps(
        feature2Api: Feature2Api,
        feature2Coordinator: Feature2Coordinator
    ): Feature2Dependencies {
        return object : Feature2Dependencies {
            override val feature2Output: Feature2Output = feature2Coordinator
            override val feature2Api: Feature2Api = feature2Api
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