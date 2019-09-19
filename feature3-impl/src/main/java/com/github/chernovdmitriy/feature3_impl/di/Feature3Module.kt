package com.github.chernovdmitriy.feature3_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature3_impl.api.Feature3Object
import dagger.Module
import dagger.Provides

@Module
class Feature3Module {

    @Feature3Scope
    @Provides
    internal fun provideFeature3Object(coreObjectApi: CoreObjectApi): Feature3Object {
        return Feature3Object(
            coreObject = coreObjectApi.coreObject.toString(),
            time = System.currentTimeMillis().toString()
        )
    }
}