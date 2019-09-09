package com.github.chernovdmitriy.multiprojectsample.di

import com.github.chernovdmitriy.core_object_api.CoreObjectApi
import com.github.chernovdmitriy.multiprojectsample.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CoreApiModule::class
])
interface AppComponent : CoreObjectApi {

    fun inject(app: App)

    companion object {
        val instance: AppComponent by lazy { DaggerAppComponent.create() }
    }

}