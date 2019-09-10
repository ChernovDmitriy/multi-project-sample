package com.github.chernovdmitriy.multiprojectsample.di.mediator

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Api
import com.github.chernovdmitriy.feature3_impl.di.DaggerFeature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Dependencies
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Feature3MediatorModule {

    @Provides
    @Singleton
    fun provideFeature3Deps(
        feature2Api: Lazy<Feature2Api>
    ): Feature3Dependencies {
        return object : Feature3Dependencies {
            override val feature2Api: Feature2Api = feature2Api.get()
        }
    }

    @Provides
    @Singleton
    fun provideFeature3Component(
        feature2Dependencies: Lazy<Feature3Dependencies>
    ): Feature3Component {
        return DaggerFeature3Component
            .builder()
            .feature3Dependencies(feature2Dependencies.get())
            .build()
    }

    @Provides
    @Singleton
    fun provideFeature3Api(feature3Component: Lazy<Feature3Component>): Feature3Api =
        feature3Component.get()

}