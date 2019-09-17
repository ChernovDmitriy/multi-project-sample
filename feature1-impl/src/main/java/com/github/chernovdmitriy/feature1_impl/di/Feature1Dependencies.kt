package com.github.chernovdmitriy.feature1_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_api.Feature1Output

interface Feature1Dependencies {

    val feature1Api: Feature1Api

    val coreObjectApi: CoreObjectApi

    val feature1Output: Feature1Output

}