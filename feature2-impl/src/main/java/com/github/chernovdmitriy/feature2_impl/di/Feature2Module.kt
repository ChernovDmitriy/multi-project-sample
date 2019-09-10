package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature2_api.Feature2Object
import dagger.Module
import dagger.Provides

@Module
class Feature2Module {

    @Feature2Scope
    @Provides
    internal fun provideFeature2Object(feature1Api: Feature1Api): Feature2Object {
        return Feature2Object(
            feature1ToString = feature1Api.feature1Object.toString(),
            time = System.currentTimeMillis().toString()
        )
    }

}