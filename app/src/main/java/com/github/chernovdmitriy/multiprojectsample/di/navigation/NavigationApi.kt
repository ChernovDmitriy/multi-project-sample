package com.github.chernovdmitriy.multiprojectsample.di.navigation

import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.app.AppCoordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinator

interface NavigationApi {

    val appCoordinator: AppCoordinator

    val mainCoordinator: MainCoordinator

    val coordinatorManager: CoordinatorManager
}