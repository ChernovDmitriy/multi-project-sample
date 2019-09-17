package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_api.Feature2Output

interface Feature2Dependencies {

    val feature2Api: Feature2Api

    val feature2Output: Feature2Output
}