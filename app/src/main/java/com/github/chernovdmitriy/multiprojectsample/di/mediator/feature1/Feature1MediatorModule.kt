package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature1

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_api.Feature1Navigator
import com.github.chernovdmitriy.feature1_impl.Feature1Dependencies
import com.github.chernovdmitriy.feature1_impl.di.DaggerFeature1Component
import com.github.chernovdmitriy.feature1_impl.di.Feature1Component
import com.github.chernovdmitriy.multiprojectsample.AppNavigator
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Feature1MediatorModule {

    @Provides
    @Singleton
    fun provideFeature1Deps(
        coreObjectApi: Lazy<CoreObjectApi>,
        appNavigator: Lazy<AppNavigator>
    ): Feature1Dependencies {
        return object : Feature1Dependencies {
            override val feature1Navigator: Feature1Navigator = appNavigator.get()
            override val coreObjectApi: CoreObjectApi = coreObjectApi.get()
        }
    }

    @Provides
    @Singleton
    fun provideFeature1Component(
        feature1Dependencies: Lazy<Feature1Dependencies>
    ): Feature1Component {
        return DaggerFeature1Component
            .builder()
            .feature1Dependencies(feature1Dependencies.get())
            .build()
    }

    @Provides
    @Singleton
    fun provideFeature1Api(feature1Component: Lazy<Feature1Component>): Feature1Api =
        feature1Component.get()

}