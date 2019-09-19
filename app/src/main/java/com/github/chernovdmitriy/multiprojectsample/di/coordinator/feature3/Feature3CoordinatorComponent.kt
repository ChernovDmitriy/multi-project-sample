package com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature3

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.feature2.Feature2CoordinatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import java.lang.ref.SoftReference

@Feature3CoordinatorScope
@Component(
    dependencies = [
        CoreObjectApi::class,
        NavigationApi::class
    ],
    modules = [Feature3CoordinatorModule::class]
)
interface Feature3CoordinatorComponent {

    fun inject(feature3ComponentProvider: Feature3ComponentProvider)

    companion object {

        private var instance: SoftReference<Feature3CoordinatorComponent>? = null

        fun newInstance(vrp: String): Feature3CoordinatorComponent {
            return provideComponent(vrp)
        }

        fun getInstance(vrp: String): Feature3CoordinatorComponent {
            return instance?.get() ?: newInstance(vrp)
        }

        private fun provideComponent(vrp: String): Feature3CoordinatorComponent {
            return DaggerFeature3CoordinatorComponent
                .builder()
                .feature3CoordinatorModule(Feature3CoordinatorModule(vrp))
                .coreObjectApi(AppComponent.instance)
                .navigationApi(AppComponent.instance)
                .build()
                .also {
                    instance = SoftReference(it)
                }
        }
    }

}