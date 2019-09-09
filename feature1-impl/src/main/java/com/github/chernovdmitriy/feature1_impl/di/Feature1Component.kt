package com.github.chernovdmitriy.feature1_impl.di

import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_impl.Feature1Dependencies
import com.github.chernovdmitriy.feature1_impl.Feature1Fragment
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import dagger.Component

@Feature1Scope
@Component(
    dependencies = [Feature1Dependencies::class],
    modules = [Feature1Module::class]
)
interface Feature1Component : Feature1Api {
    fun inject(feature1Fragment: Feature1Fragment)

//    class Initializer private constructor() {
//        companion object {
//            fun init(): Feature1Component {
//                val feature1Dependencies: Feature1Dependencies =
//                    InjectionHolderX.instance.findComponent(Feature1Dependencies::class.java)
//
//                return DaggerFeature1Component.builder()
//                    .feature1Dependencies(feature1Dependencies)
//                    .build()
//            }
//        }
//    }

}