package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature1

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import java.lang.ref.SoftReference

@Feature1CoordinatorScope
@Component(
    dependencies = [
        CoreObjectApi::class,
        NavigationApi::class
    ],
    modules = [Feature1CoordinatorModule::class]
)
interface Feature1CoordinatorComponent {

    fun feature1Api(): Feature1Api

    fun inject(feature1ComponentProvider: Feature1ComponentProvider)

    companion object {

        private var instance: SoftReference<Feature1CoordinatorComponent>? = null

        fun newInstance(): Feature1CoordinatorComponent {
            return provideComponent()
        }

        fun getInstance(): Feature1CoordinatorComponent {
            return instance?.get() ?: newInstance()
        }

        private fun provideComponent(): Feature1CoordinatorComponent {
            return DaggerFeature1CoordinatorComponent
                .builder()
                .coreObjectApi(AppComponent.instance)
                .navigationApi(AppComponent.instance)
                .build()
                .also {
                    instance = SoftReference(it)
                }
        }
    }

}