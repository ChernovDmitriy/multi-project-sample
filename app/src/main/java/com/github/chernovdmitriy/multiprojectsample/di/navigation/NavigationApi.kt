package com.github.chernovdmitriy.multiprojectsample.di.navigation

import com.github.chernovdmitriy.multiprojectsample.coordinator.app.AppCoordinator

interface NavigationApi {
    val appNavigatorImpl: AppCoordinator
}