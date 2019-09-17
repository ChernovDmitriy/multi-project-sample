package com.github.chernovdmitriy.feature3_impl.di

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature3_api.Feature3Output

interface Feature3Dependencies {

    val feature2Api: Feature2Api

    val feature3Output: Feature3Output

    val vrp: String
}