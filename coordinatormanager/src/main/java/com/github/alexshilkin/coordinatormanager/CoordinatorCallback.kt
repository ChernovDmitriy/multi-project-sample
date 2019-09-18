package com.github.alexshilkin.coordinatormanager

class CoordinatorCallback internal constructor(private val coordinatorStore: CoordinatorStore) {

    fun <T : Coordinator> addInjection(coordinatorOwner: CoordinatorOwner<T>) {
        initOrGetCoordinator(coordinatorOwner)
    }

    fun <T : Coordinator> removeInjection(coordinatorOwner: CoordinatorOwner<T>) =
        coordinatorStore.remove(coordinatorOwner.getCoordinatorKey())

    fun <T : Coordinator> getCustomOwnerLifecycle(owner: CoordinatorOwner<T>): CoordinatorOwnerLifecycle {
        return object : CoordinatorOwnerLifecycle {

            private var isInjected = false

            override fun onCreate() {
                if (!isInjected) {
                    isInjected = true
                }
            }

            override fun onFinishDestroy() {
                if (isInjected) {
                    removeInjection(owner)
                    isInjected = false
                }
            }
        }
    }

    fun addOwnerlessCoordinator(coordinator: Coordinator) =
        coordinatorStore.addOwnerLessComponent(coordinator)

    fun <T : Coordinator> removeCoordinator(coordinatorClass: Class<T>) =
        coordinatorStore.remove(coordinatorClass)

    fun <T : Coordinator> removeCoordinator(coordinatorOwner: CoordinatorOwner<T>) =
        coordinatorStore.remove(coordinatorOwner.getCoordinatorKey())

    @Suppress("UNCHECKED_CAST")
    fun <T : Coordinator> initOrGetCoordinator(owner: CoordinatorOwner<T>): T {
        val ownerKey = owner.getCoordinatorKey()

        return if (coordinatorStore.isExist(ownerKey)) {
            coordinatorStore[ownerKey] as T
        } else {
            owner.provideCoordinator().also { newCoordinator ->
                coordinatorStore.add(ownerKey, newCoordinator as Coordinator)
            }
        }
    }


    fun <T : Coordinator> initOrGetCoordinator(
        coodinatorClass: Class<T>,
        coordinatorBuilder: (() -> T)? = null
    ): T {
        val coordinator = findOrNullCoordinator(coodinatorClass)

        return (if (coordinator == null) {
            coordinatorBuilder?.invoke()?.let { newCoordinator ->
                coordinatorStore.addOwnerLessComponent(newCoordinator)
                newCoordinator
            }
        } else {
            coordinator
        })!!
    }

    fun <T : Coordinator> findCoordinator(
        coodinatorClass: Class<T>,
        coodinatorBuilder: (() -> T)? = null
    ): T = coordinatorStore.findCoordinator(coodinatorClass, coodinatorBuilder)

    fun <T : Coordinator> findOrNullCoordinator(
        coordinatorClass: Class<T>
    ): T? = coordinatorStore.findOrNullCoordinator(coordinatorClass)


}