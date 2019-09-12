package ru.mosparking.injectionholder

import android.app.Application
import ru.mosparking.injectionholder.callback.ComponentCallbackImpl
import ru.mosparking.injectionholder.registry.LifecycleCallbackRegistryImpl
import ru.mosparking.injectionholder.registry.LifecycleCallbacksRegistry
import ru.mosparking.injectionholder.storage.ComponentsStore

class InjectionHolder private constructor(lifecycleCallbacksRegistry: LifecycleCallbacksRegistry) {

    companion object {
        @JvmStatic
        val instance by lazy { InjectionHolder(LifecycleCallbackRegistryImpl()) }

        fun init(app: Application) {
            instance.componentCallback.addLifecycleCallbackListeners(app)
        }
    }

    private val componentsStore by lazy { ComponentsStore() }
    private val componentCallback: ComponentCallbackImpl by lazy {
        ComponentCallbackImpl(componentsStore, lifecycleCallbacksRegistry)
    }

    fun removeComponent(componentClass: Class<*>) = componentsStore.remove(componentClass)

    fun <T> clearComponent(owner: ComponentOwner<T>) = componentCallback.clearComponent(owner)

    fun <T> getComponent(owner: ComponentOwner<T>): T = componentCallback.getComponent(owner)

    fun <T> addOwnerlessComponent(component: T) {
        if (component != null) {
            componentsStore.addOwnerlessComponent(component)
        }
    }

    fun <T> getComponentOwnerLifeCycle(componentOwner: ComponentOwner<T>): ComponentOwnerLifecycle =
        componentCallback.getCustomOwnerLifecycle(componentOwner)

    @JvmOverloads
    fun <T> findComponent(
        componentClass: Class<T>,
        componentBuilder: (() -> T)? = null
    ): T = componentsStore.findComponent(componentClass, componentBuilder)

}