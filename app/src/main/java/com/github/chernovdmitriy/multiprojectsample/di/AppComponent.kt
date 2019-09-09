package com.github.chernovdmitriy.multiprojectsample.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.multiprojectsample.App
import com.github.chernovdmitriy.multiprojectsample.MainActivity
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationApi
import com.github.chernovdmitriy.multiprojectsample.di.navigation.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreApiModule::class,
    NavigationModule::class
]
)
interface AppComponent : CoreObjectApi, NavigationApi {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

    companion object {
        val instance: AppComponent by lazy { DaggerAppComponent.create() }
    }

}