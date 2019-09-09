package com.github.chernovdmitriy.feature2_impl.di

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.Feature2Fragment
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import dagger.Component

@Component(
    dependencies = [Feature2Dependencies::class],
    modules = [Feature2Module::class]
)
interface Feature2Component : Feature2Api {

    fun inject(feature2Fragment: Feature2Fragment)

    class Initializer private constructor() {
        companion object {
            fun init(): Feature2Component {
                val feature1Dependencies: Feature2Dependencies =
                    InjectionHolderX.instance.findComponent(Feature2Dependencies::class.java)

                return DaggerFeature2Component.builder()
                    .feature2Dependencies(feature1Dependencies)
                    .build()
            }
        }
    }

}