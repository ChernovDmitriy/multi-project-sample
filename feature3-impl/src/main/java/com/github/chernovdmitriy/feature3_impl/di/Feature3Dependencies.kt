package com.github.chernovdmitriy.feature3_impl.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature3_impl.api.Feature3Output

interface Feature3Dependencies {

    val coreObjectApi: CoreObjectApi

    val feature3Output: Feature3Output

    val vrp: String
}