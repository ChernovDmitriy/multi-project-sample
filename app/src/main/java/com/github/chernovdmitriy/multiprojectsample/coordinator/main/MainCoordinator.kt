package com.github.chernovdmitriy.multiprojectsample.coordinator.main

import androidx.fragment.app.FragmentManager
import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator
import com.github.chernovdmitriy.multiprojectsample.coordinator.CoordinatorManager
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1.Feature1Coordinator
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1.Feature1CoordinatorOutput
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2.Feature2Coordinator
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2.Feature2CoordinatorOutput
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3.Feature3Coordinator
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3.Feature3CoordinatorOutput

class MainCoordinator(
    private val mainCoordinatorOutput: MainCoordinatorOutput,
    private val coordinatorManager: CoordinatorManager
) :
    Feature1CoordinatorOutput,
    Feature2CoordinatorOutput,
    Feature3CoordinatorOutput,
    Coordinator {


    companion object {
        const val KEY = "MainCoordinator"
    }

    private var containerId: Int = 0
    private lateinit var fragmentManager: FragmentManager

    fun startMainCoordinator(containerId: Int, fragmentManager: FragmentManager) {
        this.containerId = containerId
        this.fragmentManager = fragmentManager

        (coordinatorManager.getCoordinator(Feature1Coordinator.KEY) as? Feature1Coordinator)
            ?.startFeature(
                containerId,
                fragmentManager
            )
    }

    override fun navigateToFeature2() {
        if (containerId != 0)
            (coordinatorManager.getCoordinator(Feature2Coordinator.KEY) as? Feature2Coordinator)
                ?.startFeature(
                    containerId,
                    fragmentManager
                )
    }

    override fun navigateToFeature3() {
        if (containerId != 0)
            (coordinatorManager.getCoordinator(Feature3Coordinator.KEY) as? Feature3Coordinator)
                ?.startFeature(
                    containerId,
                    fragmentManager
                )
    }

    override fun backOfFeature3() {

    }
}