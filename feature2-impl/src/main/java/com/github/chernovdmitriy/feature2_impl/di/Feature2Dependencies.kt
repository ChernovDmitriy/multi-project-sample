package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_api.Feature2Output

interface Feature2Dependencies {

    val feature1Api: Feature1Api

    val feature2Output: Feature2Output
}