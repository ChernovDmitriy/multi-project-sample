package com.github.chernovdmitriy.feature2_impl

import androidx.fragment.app.FragmentManager
import com.github.alexshilkin.coordinatormanager.Coordinator
import com.github.chernovdmitriy.feature2_impl.api.Feature2Output

class Feature2Coordinator(
    private val feature2CoordinatorOutput: Feature2CoordinatorOutput
) :
    Feature2Output, Coordinator {

    override fun clickButton() {
        feature2CoordinatorOutput.navigateToFeature3("VRP2")
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