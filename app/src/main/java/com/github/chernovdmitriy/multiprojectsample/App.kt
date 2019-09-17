package com.github.chernovdmitriy.multiprojectsample

import android.app.Application
import com.github.chernovdmitriy.injectionholdercore.ComponentOwner
import com.github.chernovdmitriy.injectionholdercore.ComponentOwnerLifecycle
import com.github.chernovdmitriy.injectionholderx.InjectionHolderX
import com.github.chernovdmitriy.multiprojectsample.di.AppComponent
import com.github.chernovdmitriy.multiprojectsample.di.coordinator.MediatorListener

class App : Application(), ComponentOwner<AppComponent> {

    private val componentOwnerLifecycle: ComponentOwnerLifecycle by lazy {
        InjectionHolderX.instance.getComponentOwnerLifeCycle(this)
    }

    override fun onCreate() {
        super.onCreate()
        InjectionHolderX.init(this)
        componentOwnerLifecycle.onCreate()
        MediatorListener.listenFeatures()
    }

    override fun inject(t: AppComponent) = t.inject(this)

    override fun provideComponent(): AppComponent = AppComponent.instance
}