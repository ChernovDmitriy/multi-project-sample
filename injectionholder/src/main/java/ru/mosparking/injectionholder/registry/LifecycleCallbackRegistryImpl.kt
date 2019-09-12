package ru.mosparking.injectionholder.registry

import android.app.Application
import ru.mosparking.injectionholder.callback.ActivityLifecycleCallback
import ru.mosparking.injectionholder.callback.ComponentCallback

internal class LifecycleCallbackRegistryImpl : LifecycleCallbacksRegistry {
    override fun registerLifecycleCallbacks(
        app: Application,
        componentCallback: ComponentCallback
    ) {
        app.registerActivityLifecycleCallbacks(ActivityLifecycleCallback(componentCallback))
    }
}