package com.github.chernovdmitriy.feature1_impl.di

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_impl.Feature1Dependencies
import com.github.chernovdmitriy.feature1_impl.Feature1Fragment
import dagger.Component

@Feature1Scope
@Component(
    dependencies = [Feature1Dependencies::class],
    modules = [Feature1Module::class]
)
interface Feature1Component : Feature1Api {
    fun inject(feature1Fragment: Feature1Fragment)
}