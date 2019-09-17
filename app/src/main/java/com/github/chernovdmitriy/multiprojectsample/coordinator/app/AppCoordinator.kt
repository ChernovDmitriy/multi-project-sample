package com.github.chernovdmitriy.multiprojectsample.coordinator.app

import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.main.MainCoordinatorOutput

class AppCoordinator(private val coordinatorManager: CoordinatorManager) : MainCoordinatorOutput,
    Coordinator {

    companion object {
        const val KEY = "AppCoordinator"
    }

    fun start() {
    }

    override fun navigateTo() {
    }
}