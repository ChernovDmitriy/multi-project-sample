package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature2_impl.api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.Feature2Fragment
import dagger.Component

@Feature2Scope
@Component(
    dependencies = [Feature2Dependencies::class],
    modules = [Feature2Module::class]
)
interface Feature2Component : Feature2Api {

    fun inject(feature2Fragment: Feature2Fragment)

}