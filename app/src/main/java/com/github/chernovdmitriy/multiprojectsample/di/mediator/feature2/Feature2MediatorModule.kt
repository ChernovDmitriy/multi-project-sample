package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_api.Feature2Navigator
import com.github.chernovdmitriy.feature2_impl.di.DaggerFeature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Component
import com.github.chernovdmitriy.feature2_impl.di.Feature2Dependencies
import com.github.chernovdmitriy.multiprojectsample.AppNavigator
import dagger.Module
import dagger.Provides

@Module
class Feature2MediatorModule {

    private var component: Feature2Component? = null

    @Provides
//    @Feature2MediatorScope
    fun provideFeature2Deps(
        feature1Api: Feature1Api,
        appNavigator: AppNavigator
    ): Feature2Dependencies {
        return object : Feature2Dependencies {
            override val feature2Navigator: Feature2Navigator = appNavigator
            override val feature1Api: Feature1Api = feature1Api
        }
    }

    @Provides
//    @Feature2MediatorScope
    fun provideFeature2Component(
        feature2Dependencies: Feature2Dependencies
    ): Feature2Component {
        return provideComponent(feature2Dependencies)
    }


    @Provides
//    @Feature2MediatorScope
    fun provideFeature2Api(feature2Dependencies: Feature2Dependencies): Feature2Api =
        component ?: provideComponent(feature2Dependencies)

    private fun provideComponent(feature2Dependencies: Feature2Dependencies): Feature2Component {
        return DaggerFeature2Component
            .builder()
            .feature2Dependencies(feature2Dependencies)
            .build()
            .also { component = it }
    }

}