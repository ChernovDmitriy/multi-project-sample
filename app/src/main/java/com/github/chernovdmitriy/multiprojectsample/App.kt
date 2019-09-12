package com.github.chernovdmitriy.multiprojectsample

import android.app.Application
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.mediator.MediatorListener
import ru.mosparking.injectionholder.ComponentOwner
import ru.mosparking.injectionholder.ComponentOwnerLifecycle
import ru.mosparking.injectionholder.InjectionHolder

class App : Application(), ComponentOwner<AppComponent> {

    private val componentOwnerLifecycle: ComponentOwnerLifecycle by lazy {
        InjectionHolder.instance.getComponentOwnerLifeCycle(this)
    }

    override fun onCreate() {
        super.onCreate()
        InjectionHolder.init(this)
        componentOwnerLifecycle.onCreate()
        MediatorListener.listenFeatures()
    }

    override fun inject(t: AppComponent) = t.inject(this)

    override fun provideComponent(): AppComponent = AppComponent.instance
}