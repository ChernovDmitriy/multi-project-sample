package com.github.chernovdmitriy.feature1_impl

import androidx.fragment.app.FragmentManager
import com.github.alexshilkin.coordinatormanager.Coordinator
import com.github.chernovdmitriy.feature1_api.Feature1Output
import com.github.chernovdmitriy.feature1_impl.Feature1CoordinatorOutput
import com.github.chernovdmitriy.feature1_impl.Feature1Fragment

class Feature1Coordinator(
    private val feature1CoordinatorOutput: Feature1CoordinatorOutput
) : Feature1Output, Coordinator {

    override fun clickBottom1() {
        feature1CoordinatorOutput.navigateToFeature2()
    }

    override fun clickButton2() {
        feature1CoordinatorOutput.navigateToFeature3("VRP1")
    }

    fun startFeature(containerId: Int, fragmentManager: FragmentManager) {
        val fragment = Feature1Fragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("Feature1Fragment")
            .commit()
    }

}