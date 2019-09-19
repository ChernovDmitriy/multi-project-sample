package com.github.alexshilkin.coordinatormanager

abstract class CoordinatorHolder<ApplicationType>(
    private val lifecycleCallbacksRegistry: LifecycleCallbacksRegistry<ApplicationType>
) {

    private val coordinatorCallback: CoordinatorCallback by lazy {
        CoordinatorCallback(CoordinatorStore())
    }

    protected fun init(application: ApplicationType) =
        lifecycleCallbacksRegistry.registerLifecycleCallbacks(application, coordinatorCallback)

    fun <T : Coordinator> addOwnerlessCoordinator(coordinator: T) =
        coordinatorCallback.addOwnerlessCoordinator(coordinator)

    fun removeCoordinator(coordinatorClass: Class<Coordinator>) =
        coordinatorCallback.removeCoordinator(coordinatorClass)

    fun <T : Coordinator> removeCoordinator(owner: CoordinatorOwner<T>) =
        coordinatorCallback.removeCoordinator(owner)

    fun <T : Coordinator> getComponent(owner: CoordinatorOwner<T>): T =
        coordinatorCallback.initOrGetCoordinator(owner)

    fun <T : Coordinator> getComponentOrInit(
        coodinatorClass: Class<T>,
        coordinatorBuilder: (() -> T)? = null
    ): T = coordinatorCallback.initOrGetCoordinator(coodinatorClass, coordinatorBuilder)

    fun <T : Coordinator> getCoordinatorOwnerLifeCycle(coordinatorOwner: CoordinatorOwner<T>): CoordinatorOwnerLifecycle =
        coordinatorCallback.getCustomOwnerLifecycle(coordinatorOwner)

    @JvmOverloads
    fun <T : Coordinator> findCoordinator(
        coordinatorClass: Class<T>,
        coordinatorBuilder: (() -> T)? = null
    ): T = coordinatorCallback.findCoordinator(coordinatorClass, coordinatorBuilder)

    @JvmOverloads
    fun <T : Coordinator> findOrNullCoordinator(
        coordinatorClass: Class<T>
    ): T? = coordinatorCallback.findOrNullCoordinator(coordinatorClass)

}