package com.github.chernovdmitriy.feature1_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_impl.api.Feature1Output

interface Feature1Dependencies {

    val coreObjectApi: CoreObjectApi

    val feature1Output: Feature1Output

}