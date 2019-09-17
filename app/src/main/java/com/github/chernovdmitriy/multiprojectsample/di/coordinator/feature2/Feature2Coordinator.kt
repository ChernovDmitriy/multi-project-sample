package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2

import androidx.fragment.app.FragmentManager
import com.github.chernovdmitriy.feature2_api.Feature2Output
import com.github.chernovdmitriy.feature2_impl.Feature2Fragment
import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator

class Feature2Coordinator(private val feature2CoordinatorOutput: Feature2CoordinatorOutput) :
    Feature2Output, Coordinator {

    companion object {
        const val KEY = "Feature2Coordinator"
    }

    override fun clickButton() {
        feature2CoordinatorOutput.navigateToFeature3()
    }

    fun startFeature(containerId: Int, fragmentManager: FragmentManager) {
        val fragment =
            Feature2Fragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("Feature2Fragment")
            .commit()
    }
}