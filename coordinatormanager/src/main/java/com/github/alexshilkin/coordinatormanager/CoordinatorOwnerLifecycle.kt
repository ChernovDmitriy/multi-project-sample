package com.github.alexshilkin.coordinatormanager

interface CoordinatorOwnerLifecycle {

    fun onCreate()

    fun onFinishDestroy()
}