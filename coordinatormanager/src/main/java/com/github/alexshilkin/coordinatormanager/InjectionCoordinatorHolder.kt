package com.github.alexshilkin.coordinatormanager

import android.app.Application

class InjectionCoordinatorHolder private constructor() :
    CoordinatorHolder<Application>(AndroidXLifecycleCallbacksRegistry()) {

    companion object {
        @JvmStatic
        val instance by lazy { InjectionCoordinatorHolder() }

        fun init(application: Application) = instance.init(application)
    }

    private class AndroidXLifecycleCallbacksRegistry : LifecycleCallbacksRegistry<Application> {
        override fun registerLifecycleCallbacks(
            app: Application,
            coordinatorCallback: CoordinatorCallback
        ) =
            app.registerActivityLifecycleCallbacks(ActivityLifecycleCallback(coordinatorCallback))
    }

}