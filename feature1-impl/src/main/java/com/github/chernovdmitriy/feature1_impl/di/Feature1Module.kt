package com.github.chernovdmitriy.feature1_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Object
import dagger.Module
import dagger.Provides

@Module
class Feature1Module {

    @Feature1Scope
    @Provides
    fun provideFeature1Object(coreObjectApi: CoreObjectApi): Feature1Object {
        return Feature1Object(
            coreObjectToString = coreObjectApi.coreObject.toString()
        )
    }

}