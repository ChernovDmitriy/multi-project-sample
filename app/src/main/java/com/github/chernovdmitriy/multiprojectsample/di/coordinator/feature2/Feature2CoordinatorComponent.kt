package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2

import com.github.chernovdmitriy.feature2_api.Feature2Api
import com.github.chernovdmitriy.feature2_impl.di.Feature2ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1.Feature1CoordinatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import java.lang.ref.SoftReference

@Feature2CoordinatorScope
@Component(
    dependencies = [
        NavigationApi::class,
        Feature1CoordinatorComponent::class
    ],
    modules = [Feature2CoordinatorModule::class]
)
interface Feature2CoordinatorComponent {

    fun feature2Api(): Feature2Api

    fun inject(feature2ComponentProvider: Feature2ComponentProvider)

    companion object {

        private var instance: SoftReference<Feature2CoordinatorComponent>? = null

        fun newInstance(): Feature2CoordinatorComponent {
            return provideComponent()
        }

        fun getInstance(): Feature2CoordinatorComponent {
            return instance?.get() ?: newInstance()
        }

        private fun provideComponent(): Feature2CoordinatorComponent {
            return DaggerFeature2Component
                .builder()
                .feature1MediatorComponent(Feature1CoordinatorComponent.getInstance())
                .navigationApi(AppComponent.instance)
                .build()
                .also {
                    instance = SoftReference(it)
                }
        }
    }
}