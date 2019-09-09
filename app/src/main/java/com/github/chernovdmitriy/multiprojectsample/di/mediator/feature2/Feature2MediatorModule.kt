package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.di.DaggerFeature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Dependencies
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.mediator.MediatorComponent
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Feature2MediatorModule {

    @Provides
    @Singleton
    fun provideFeature2Deps(feature1Api: Lazy<Feature1Api>): Feature2Dependencies {
        return object : Feature2Dependencies {
            override val feature1Api: Feature1Api = feature1Api.get()
        }
    }

    @Provides
    @Singleton
    fun provideFeature1Component(
        feature1Dependencies: Lazy<Feature2Dependencies>
    ): Feature2Component {
        return DaggerFeature2Component
            .builder()
            .feature2Dependencies(feature1Dependencies.get())
            .build()
    }

    @Provides
    @Singleton
    fun provideFeature1Api(feature2Component: Lazy<Feature2Component>): Feature2Api =
        feature2Component.get()

}