package com.github.chernovdmitriy.feature3_impl

import androidx.fragment.app.FragmentManager
import com.github.alexshilkin.coordinatormanager.Coordinator
import com.github.chernovdmitriy.feature3_api.Feature3Output

class Feature3Coordinator(
    private val feature3CoordinatorOutput: Feature3CoordinatorOutput
) : Feature3Output, Coordinator {

    override fun back() {
        feature3CoordinatorOutput.backOfFeature3()
    }

    fun startFeature(containerId: Int, fragmentManager: FragmentManager, vrp: String) {
        val fragment =
            Feature3Fragment.newInstance(vrp)

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack("Feature3Fragment")
            .commit()
    }
}