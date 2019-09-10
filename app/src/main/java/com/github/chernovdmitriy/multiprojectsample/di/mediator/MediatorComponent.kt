package com.github.chernovdmitriy.multiprojectsample.di.mediator

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CoreObjectApi::class,
        NavigationApi::class
    ],
    modules = [
        Feature1MediatorModule::class,
        Feature2MediatorModule::class,
        Feature3MediatorModule::class
    ]
)
interface MediatorComponent {

    fun inject(feature1ComponentProvider: Feature1ComponentProvider)
    fun inject(feature2ComponentProvider: Feature2ComponentProvider)
    fun inject(feature3ComponentProvider: Feature3ComponentProvider)

    companion object {

        val instance: MediatorComponent by lazy {
            val appComponent: AppComponent =
                InjectionHolderX.instance.findComponent(AppComponent::class.java)

            DaggerMediatorComponent
                .builder()
                .coreObjectApi(appComponent)
                .navigationApi(appComponent)
                .build()
        }
    }

}