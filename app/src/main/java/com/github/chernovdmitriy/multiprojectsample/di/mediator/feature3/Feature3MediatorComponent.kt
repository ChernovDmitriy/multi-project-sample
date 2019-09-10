package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature3

import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2.Feature2MediatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component

@Feature3MediatorScope
@Component(
    dependencies = [
        Feature2MediatorComponent::class,
        NavigationApi::class
    ],
    modules = [Feature3MediatorModule::class]
)
interface Feature3MediatorComponent {

    fun inject(feature3ComponentProvider: Feature3ComponentProvider)

    companion object {
        val instance: Feature3MediatorComponent by lazy {
            DaggerFeature3MediatorComponent
                .builder()
                .feature2MediatorComponent(Feature2MediatorComponent.instance)
                .navigationApi(AppComponent.instance)
                .build()
        }
    }

}