package com.github.chernovdmitriy.feature3_impl.di

import com.github.chernovdmitriy.feature3_impl.api.Feature3Api
import com.github.chernovdmitriy.feature3_impl.Feature3Fragment
import dagger.Component

@Feature3Scope
@Component(
    dependencies = [Feature3Dependencies::class],
    modules = [Feature3Module::class]
)
interface Feature3Component : Feature3Api {

    fun inject(feature3Fragment: Feature3Fragment)

}