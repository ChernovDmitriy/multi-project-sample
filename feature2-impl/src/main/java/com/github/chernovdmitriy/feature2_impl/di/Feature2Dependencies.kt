package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature2_impl.api.Feature2Output


interface Feature2Dependencies {

    val coreObjectApi: CoreObjectApi

    val feature2Output: Feature2Output
}