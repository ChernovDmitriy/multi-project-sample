package ru.mosparking.injectionholder.storage

import ru.mosparking.injectionholder.exception.ComponentNotFoundException

internal class ComponentsStore {

    private val components = mutableMapOf<String, Any>()

    private val ownerlessComponents = hashSetOf<Any>()

    fun addOwnerlessComponent(component: Any) {
        ownerlessComponents.add(component)
    }

    fun add(key: String, component: Any) {
        components[key] = component
        ownerlessComponents.remove(component)
    }

    operator fun get(key: String): Any = components[key] ?: throw ComponentNotFoundException(key)

    fun remove(ownerKey: String) {
        val component = components.remove(ownerKey)
        component?.let { ownerlessComponents.remove(component) }
    }

    fun remove(componentClass: Class<*>): Boolean {

        var searchedComponent: Any? = null
        for (component in components.values) {
            if (component.isSameClass(componentClass)) {
                searchedComponent = component
                break
            }
        }
        searchedComponent?.let {
            ownerlessComponents.remove(it)
            return components.values.remove(it)
        }
        return false
    }

    fun isExist(key: String) = components.containsKey(key)

    @Suppress("UNCHECKED_CAST")
    fun <T> findComponent(
        searchedClass: Class<T>,
        componentBuilder: (() -> T)?
    ): T {

        components.values.forEach { component ->
            if (component.isSameClass(searchedClass)) {
                return component as T
            }
        }

        //else
        ownerlessComponents.forEach { component ->
            if (component.isSameClass(searchedClass)) {
                return component as T
            }
        }

        //else
        componentBuilder?.invoke()?.let { newComponent ->
            return newComponent
        }

        //else
        throw ComponentNotFoundException(searchedClass.simpleName)
    }

    private fun Any.isSameClass(otherClass: Class<*>): Boolean =
        otherClass.isAssignableFrom(javaClass) || javaClass.isAssignableFrom(otherClass)

}