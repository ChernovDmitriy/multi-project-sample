package com.github.alexshilkin.coordinatormanager

class CoordinatorStore {

    private val coordinators = hashMapOf<String, Coordinator>()
    private val ownerLessCoordinators = hashSetOf<Coordinator>()


    fun addOwnerLessComponent(coordinator: Coordinator) {
        ownerLessCoordinators.add(coordinator)
    }

    fun add(key: String, coordinator: Coordinator) {
        coordinators[key] = coordinator
        ownerLessCoordinators.remove(coordinator)
    }

    operator fun get(key: String): Coordinator =
        coordinators[key] ?: throw CoordinatorNotFoundException(key)

    fun remove(key: String) {
        val coordinator = coordinators.remove(key)
        coordinator?.let { ownerLessCoordinators.remove(it) }
    }

    fun remove(coordinatorClass: Class<*>): Boolean {
        var searchedCoordinator: Any? = null

        for (coordinator in coordinators.values) {
            if (coordinator.isSameClass(coordinatorClass)) {
                searchedCoordinator = coordinator
                break
            }
        }

        searchedCoordinator?.let {
            ownerLessCoordinators.remove(it)
            return coordinators.values.remove(it)
        }

        return false
    }

    fun isExist(key: String) = coordinators.containsKey(key)

    @Suppress("UNCHECKED_CAST")
    fun <T> findCoordinator(
        searchedClass: Class<T>,
        coordinatorBuilder: (() -> T)?
    ): T {

        coordinators.values.forEach { coordinator ->
            if (coordinator.isSameClass(searchedClass)) {
                return coordinator as T
            }
        }

        //else
        ownerLessCoordinators.forEach { coordinator ->
            if (coordinator.isSameClass(searchedClass)) {
                return coordinator as T
            }
        }

        //else
        coordinatorBuilder?.invoke()?.let { newCoordinator -> return newCoordinator }

        //else
        throw CoordinatorNotFoundException(searchedClass.simpleName)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> findOrNullCoordinator(
        searchedClass: Class<T>
    ): T? {

        coordinators.values.forEach { coordinator ->
            if (coordinator.isSameClass(searchedClass)) {
                return coordinator as T?
            }
        }

        //else
        ownerLessCoordinators.forEach { coordinator ->
            if (coordinator.isSameClass(searchedClass)) {
                return coordinator as T?
            }
        }

        return null
    }

    private fun Any.isSameClass(otherClass: Class<*>): Boolean =
        otherClass.isAssignableFrom(javaClass) || javaClass.isAssignableFrom(otherClass)
}