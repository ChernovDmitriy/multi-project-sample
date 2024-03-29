package ru.mosparking.injectionholder

interface ComponentOwner<T> {

    fun provideComponent(): T

    fun getComponentKey(): String = javaClass.toString()

    fun inject(t: T)

}