package ru.mosparking.injectionholder.callback

import ru.mosparking.injectionholder.ComponentOwner

internal interface ComponentCallback {

    fun <T> onAddInjection(componentOwner: ComponentOwner<T>)

    fun <T> onRemoveInjection(componentOwner: ComponentOwner<T>)
}