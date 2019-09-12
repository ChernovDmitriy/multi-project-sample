package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature3

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Api
import com.github.chernovdmitriy.feature3_impl.di.DaggerFeature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Component
import com.github.chernovdmitriy.feature3_impl.di.Feature3Dependencies
import dagger.Module
import dagger.Provides
import ru.mosparking.injectionholder.InjectionHolder

@Module
class Feature3MediatorModule {

    @Provides
    @Feature3MediatorScope
    fun provideFeature3Deps(
        feature2Api: Feature2Api
    ): Feature3Dependencies {
        return object : Feature3Dependencies {
            override val feature2Api: Feature2Api = feature2Api
        }
    }

    @Provides
    @Feature3MediatorScope
    fun provideFeature3Component(
        feature3Dependencies: Feature3Dependencies
    ): Feature3Component {
        return DaggerFeature3Component
            .builder()
            .feature3Dependencies(feature3Dependencies)
            .build()
            .also {
                InjectionHolder.instance.addOwnerlessComponent(it)
            }
    }


    @Provides
    @Feature3MediatorScope
    fun provideFeature3Api(
        feature3Component: Feature3Component
    ): Feature3Api =
        feature3Component

}