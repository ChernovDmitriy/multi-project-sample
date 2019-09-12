package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature1

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_api.Feature1Navigator
import com.github.chernovdmitriy.feature1_impl.Feature1Dependencies
import com.github.chernovdmitriy.feature1_impl.di.DaggerFeature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.AppNavigator
import dagger.Module
import dagger.Provides

@Module
class Feature1MediatorModule {

    @Provides
    @Feature1MediatorScope
    fun provideFeature1Deps(
        coreObjectApi: CoreObjectApi,
        appNavigator: AppNavigator
    ): Feature1Dependencies {
        return object : Feature1Dependencies {
            override val feature1Navigator: Feature1Navigator = appNavigator
            override val coreObjectApi: CoreObjectApi = coreObjectApi
        }
    }

    @Provides
    @Feature1MediatorScope
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
    @Feature1MediatorScope
    fun provideFeature1Api(
        feature1Component: Feature1Component
    ): Feature1Api =
        feature1Component

}