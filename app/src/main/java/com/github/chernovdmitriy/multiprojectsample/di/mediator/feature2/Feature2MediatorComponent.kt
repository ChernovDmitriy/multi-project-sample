package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature1.Feature1MediatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component

@Feature2MediatorScope
@Component(
    dependencies = [
        NavigationApi::class,
        Feature1MediatorComponent::class
    ],
    modules = [Feature2MediatorModule::class]
)
interface Feature2MediatorComponent {

    fun feature2Api(): Feature2Api

    fun inject(feature2ComponentProvider: Feature2ComponentProvider)

    companion object {

        private var instance: Feature2MediatorComponent? = null

        fun newInstance(): Feature2MediatorComponent {
            return provideComponent()
        }

        fun getInstance(): Feature2MediatorComponent {
            return instance ?: newInstance()
        }

        private fun provideComponent(): Feature2MediatorComponent {
            return DaggerFeature2MediatorComponent
                .builder()
                .feature1MediatorComponent(Feature1MediatorComponent.getInstance())
                .navigationApi(AppComponent.instance)
                .build()
                .also {
                    instance = it
                }
        }
    }
}