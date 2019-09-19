package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature2_impl.api.Feature2Object
import dagger.Module
import dagger.Provides

@Module
class Feature2Module {

    @Feature2Scope
    @Provides
    internal fun provideFeature2Object(coreObjectApi: CoreObjectApi): Feature2Object {
        return Feature2Object(
            coreObject = coreObjectApi.coreObject.toString(),
            time = System.currentTimeMillis().toString()
        )
    }

}