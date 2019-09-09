package com.github.chernovdmitriy.multiprojectsample.di.navigation

import com.github.chernovdmitriy.multiprojectsample.AppNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    internal fun provideAppNavigator() = AppNavigator()

}