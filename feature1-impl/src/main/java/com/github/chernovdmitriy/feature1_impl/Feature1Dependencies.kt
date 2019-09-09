package com.github.chernovdmitriy.feature1_impl

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Navigator

interface Feature1Dependencies {

    val coreObjectApi: CoreObjectApi

    val feature1Navigator: Feature1Navigator

}