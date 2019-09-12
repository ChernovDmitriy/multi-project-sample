package ru.mosparking.injectionholder.registry

import android.app.Application
import ru.mosparking.injectionholder.callback.ComponentCallback

internal interface LifecycleCallbacksRegistry {
    fun registerLifecycleCallbacks(app: Application, componentCallback: ComponentCallback)
}