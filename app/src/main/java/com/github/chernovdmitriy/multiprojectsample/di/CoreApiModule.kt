package com.github.chernovdmitriy.multiprojectsample.di

import com.github.chernovdmitriy.core_object_api.CoreObject
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreApiModule {

    @Singleton
    @Provides
    internal fun provideCoreObject() = CoreObject()

}