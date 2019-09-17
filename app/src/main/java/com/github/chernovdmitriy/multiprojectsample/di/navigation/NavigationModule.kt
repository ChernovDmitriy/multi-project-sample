package com.github.chernovdmitriy.multiprojectsample.di.navigation

import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2.Feature2Coordinator
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3.Feature3Coordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.app.AppCoordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    internal fun provideCoordinatorManage() = CoordinatorManager()

    @Singleton
    @Provides
    internal fun provideAppCoordinator(coordinatorManager: CoordinatorManager): AppCoordinator {
        val coordinator = AppCoordinator(coordinatorManager)
        coordinatorManager.addCoordinator(AppCoordinator.KEY, coordinator)
        return coordinator
    }

    @Singleton
    @Provides
    internal fun provideMainNavigator(
        appCoordinator: AppCoordinator,
        coordinatorManager: CoordinatorManager
    ): MainCoordinator {
        val coordinator = MainCoordinator(appCoordinator, coordinatorManager)
        coordinatorManager.addCoordinator(MainCoordinator.KEY, coordinator)
        return coordinator
    }


}