package com.github.chernovdmitriy.multiprojectsample

import androidx.navigation.NavController
import com.github.chernovdmitriy.feature1_api.Feature1Navigator
import com.github.chernovdmitriy.feature2_api.Feature2Navigator

class AppNavigator : Feature1Navigator, Feature2Navigator {
    private var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }

    override fun moveToFeature2() {
        navController?.navigate(R.id.action_feature1Fragment_to_feature2Fragment)
    }

}