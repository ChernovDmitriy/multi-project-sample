package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature1

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.feature1_api.Feature1Api
import com.github.chernovdmitriy.feature1_impl.di.Feature1ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import java.lang.ref.SoftReference

@Feature1MediatorScope
@Component(
    dependencies = [
        CoreObjectApi::class,
        NavigationApi::class
    ],
    modules = [Feature1MediatorModule::class]
)
interface Feature1MediatorComponent {

    fun feature1Api(): Feature1Api

    fun inject(feature1ComponentProvider: Feature1ComponentProvider)

    companion object {

        private var instance: SoftReference<Feature1MediatorComponent>? = null

        fun newInstance(): Feature1MediatorComponent {
            return provideComponent()
        }

        fun getInstance(): Feature1MediatorComponent {
            return instance?.get() ?: newInstance()
        }

        private fun provideComponent(): Feature1MediatorComponent {
            return DaggerFeature1MediatorComponent
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