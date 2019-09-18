package com.github.chernovdmitriy.multiprojectsample.di.navigation

import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    internal fun provideMainNavigator(): MainCoordinator {
        return MainCoordinator().also {
            InjectionCoordinatorHolder.instance.addOwnerlessCoordinator(
                it
            )
        }
    }
}