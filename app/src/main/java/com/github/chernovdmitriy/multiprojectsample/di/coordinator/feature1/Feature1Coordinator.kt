package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1

import androidx.fragment.app.FragmentManager
import com.github.chernovdmitriy.feature1_api.Feature1Output
import com.github.chernovdmitriy.feature1_impl.Feature1Fragment
import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator

class Feature1Coordinator(
    private val feature1CoordinatorOutput: Feature1CoordinatorOutput
) : Feature1Output, Coordinator {

    companion object {
        const val KEY = "Feature1Coordinator"
    }

    override fun clickBottom1() {
        feature1CoordinatorOutput.navigateToFeature2()
    }

    override fun clickButton2() {
        feature1CoordinatorOutput.navigateToFeature3()
    }

    fun startFeature(containerId: Int, fragmentManager: FragmentManager) {
        val fragment = Feature1Fragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("Feature1Fragment")
            .commit()
    }

}