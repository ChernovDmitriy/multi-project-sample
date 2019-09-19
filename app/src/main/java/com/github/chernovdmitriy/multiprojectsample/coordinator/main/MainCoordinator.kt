package com.github.chernovdmitriy.multiprojectsample.coordinator.main

import androidx.fragment.app.FragmentManager
import com.github.alexshilkin.coordinatormanager.Coordinator
import com.github.alexshilkin.coordinatormanager.InjectionCoordinatorHolder
import com.github.chernovdmitriy.feature1_impl.Feature1Coordinator
import com.github.chernovdmitriy.feature1_impl.Feature1CoordinatorOutput
import com.github.chernovdmitriy.feature2_impl.Feature2Coordinator
import com.github.chernovdmitriy.feature2_impl.Feature2CoordinatorOutput
import com.github.chernovdmitriy.feature3_impl.Feature3Coordinator
import com.github.chernovdmitriy.feature3_impl.Feature3CoordinatorOutput

class MainCoordinator :
    Feature1CoordinatorOutput,
    Feature2CoordinatorOutput,
    Feature3CoordinatorOutput,
    Coordinator {

    private var containerId: Int = 0
    private lateinit var fragmentManager: FragmentManager

    fun startMainCoordinator(containerId: Int, fragmentManager: FragmentManager) {
        this.containerId = containerId
        this.fragmentManager = fragmentManager

        val coordinatorRestored =
            InjectionCoordinatorHolder.instance.findOrNullCoordinator(Feature1Coordinator::class.java)
        if (coordinatorRestored == null) {
            val coordinator = InjectionCoordinatorHolder.instance.getComponentOrInit(
                coodinatorClass = Feature1Coordinator::class.java,
                coordinatorBuilder = { Feature1Coordinator(this) }
            )
            coordinator.startFeature(
                containerId,
                fragmentManager
            )
        }
    }

    override fun navigateToFeature2() {
        if (containerId != 0) {
            val coordinator = InjectionCoordinatorHolder.instance.getComponentOrInit(
                coodinatorClass = Feature2Coordinator::class.java,
                coordinatorBuilder = { Feature2Coordinator(this) }
            )
            coordinator.startFeature(
                containerId,
                fragmentManager
            )
        }

    }

    override fun navigateToFeature3(vrp: String) {
        if (containerId != 0) {
            val coordinator = InjectionCoordinatorHolder.instance.getComponentOrInit(
                coodinatorClass = Feature3Coordinator::class.java,
                coordinatorBuilder = { Feature3Coordinator(this) }
            )
            coordinator.startFeature(
                containerId,
                fragmentManager,
                vrp
            )
        }
    }

    override fun backOfFeature3() {

    }
}