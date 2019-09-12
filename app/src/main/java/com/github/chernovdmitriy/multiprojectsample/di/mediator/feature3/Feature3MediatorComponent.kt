package com.github.chernovdmitriy.multiprojectsample.di.mediator.feature3

import com.github.chernovdmitriy.feature3_impl.di.Feature3ComponentProvider
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.feature2.Feature2MediatorComponent
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import dagger.Component
import java.lang.ref.SoftReference

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

        private var instance: SoftReference<Feature3MediatorComponent>? = null

        fun newInstance(): Feature3MediatorComponent {
            return provideComponent()
        }

        fun getInstance(): Feature3MediatorComponent {
            return instance?.get() ?: newInstance()
        }

        private fun provideComponent(): Feature3MediatorComponent {
            return DaggerFeature3MediatorComponent
                .builder()
                .feature2MediatorComponent(Feature2MediatorComponent.getInstance())
                .navigationApi(AppComponent.instance)
                .build()
                .also {
                    instance = SoftReference(it)
                }
        }
    }

}