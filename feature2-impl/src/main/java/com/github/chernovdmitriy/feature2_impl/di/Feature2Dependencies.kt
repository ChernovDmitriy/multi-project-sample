package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature2_api.Feature2Navigator

interface Feature2Dependencies {

    val feature1Api: Feature1Api

    val feature2Navigator: Feature2Navigator

}