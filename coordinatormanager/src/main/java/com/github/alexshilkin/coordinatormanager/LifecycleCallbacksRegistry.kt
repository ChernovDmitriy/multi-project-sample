package com.github.alexshilkin.coordinatormanager

interface LifecycleCallbacksRegistry<ApplicationType> {
    fun registerLifecycleCallbacks(app: ApplicationType, coordinatorCallback: CoordinatorCallback)
}