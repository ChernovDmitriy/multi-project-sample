package com.github.chernovdmitriy.feature3_impl.di

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Api
import com.github.chernovdmitriy.feature3_api.Feature3Object
import dagger.Module
import dagger.Provides

@Module
class Feature3Module {

    @Feature3Scope
    @Provides
    internal fun provideFeature3Object(feature2Api: Feature2Api): Feature3Object {
        return Feature3Object(
            feature2ToString = feature2Api.feature2Object.toString(),
            time = System.currentTimeMillis().toString()
        )
    }
}