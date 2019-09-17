package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3

import androidx.fragment.app.FragmentManager
import com.github.chernovdmitriy.feature3_api.Feature3Output
import com.github.chernovdmitriy.feature3_impl.Feature3Fragment
import com.github.chernovdmitriy.multiprojectsample.coordinator.Coordinator

class Feature3Coordinator(private val feature3CoordinatorOutput: Feature3CoordinatorOutput) :
    Feature3Output, Coordinator {

    companion object {
        const val KEY = "Feature3Coordinator"
    }

    override fun back() {
        feature3CoordinatorOutput.backOfFeature3()
    }

    fun startFeature(containerId: Int, fragmentManager: FragmentManager) {
        val fragment =
            Feature3Fragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("Feature3Fragment")
            .commit()
    }
}