package com.github.alexshilkin.coordinatormanager

interface CoordinatorOwner<T : Coordinator> {

    fun startCoordinator(coordinator: T)

    fun provideCoordinator(): T

    fun getCoordinatorKey(): String = javaClass.toString()
}