package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature3

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Api
import com.github.chernovdmitriy.feature3_impl.di.DaggerFeature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Dependencies
import dagger.Module
import dagger.Provides

@Module
class Feature3MediatorModule {

    private var component: Feature3Component? = null

    @Provides
//    @Feature3MediatorScope
    fun provideFeature3Deps(
        feature2Api: Feature2Api
    ): Feature3Dependencies {
        return object : Feature3Dependencies {
            override val feature2Api: Feature2Api = feature2Api
        }
    }

    @Provides
//    @Feature3MediatorScope
    fun provideFeature3Component(
        feature3Dependencies: Feature3Dependencies
    ): Feature3Component {
        return provideComponent(feature3Dependencies)
    }


    @Provides
//    @Feature3MediatorScope
    fun provideFeature3Api(feature3Dependencies: Feature3Dependencies): Feature3Api =
        component ?: provideComponent(feature3Dependencies)

    private fun provideComponent(feature3Dependencies: Feature3Dependencies): Feature3Component {
        return DaggerFeature3Component
            .builder()
            .feature3Dependencies(feature3Dependencies)
            .build()
            .also { component = it }
    }

}